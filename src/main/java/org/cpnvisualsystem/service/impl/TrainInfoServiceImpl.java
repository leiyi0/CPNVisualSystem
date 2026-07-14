package org.cpnvisualsystem.service.impl;

import java.util.Comparator;
import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ResourceSummary;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.CarriagePreviewVO;
import org.cpnvisualsystem.entity.vo.CarriageResourceVO;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.PreviewWrapper;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainInfoVO;
import org.cpnvisualsystem.entity.vo.TrainViewVO;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.ResourceSummaryMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.service.TrainInfoService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainInfoServiceImpl implements TrainInfoService {

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private StaticPowerService staticPowerService;

    @Autowired
    private ResourceSummaryMapper resourceSummaryMapper;

    @Override
    public TrainInfoVO getTrainById(Integer trainId) {
        TrainInfo train = trainInfoMapper.selectById(trainId);
        if (train == null) return null;
        train.setCarriageCount(carriageInfoMapper.countCarriagesByTrainId(trainId));
        TrainInfoVO vo = TransformUtil.toTrainInfo(train);

        // 填充静态算力总量
        StaticPowerInfo staticPower = staticPowerService.getStaticPowerByTrainId(trainId);
        if (staticPower != null) {
            vo.setTotalComputePower(staticPower.getComputerPower());
            vo.setTotalComputePowerMips(staticPower.getComputerPowerMips());
            vo.setTotalStoragePower(staticPower.getStoragePower());
            vo.setTotalTransportPower(staticPower.getTransportPower());
        }

        // 从 resource_summary 补充 MIPS 计算力总量
        ResourceSummary summary = resourceSummaryMapper.selectByLayer("train", trainId);
        if (summary != null && summary.getComputeMipsTotal() != null) {
            vo.setTotalComputePowerMips(summary.getComputeMipsTotal());
        }

        // 填充任务汇总
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByTrainId(trainId);
        if (tasks != null && !tasks.isEmpty()) {
            vo.setTaskCount(tasks.size());
            double computeSum = 0, computeMipsSum = 0, storageSum = 0, transportSum = 0;
            for (TaskInfo t : tasks) {
                if (t.getComputeDemand() != null) {
                    boolean isMips = t.getComputeType() != null && t.getComputeType().toLowerCase().contains("mips");
                    if (isMips) {
                        computeMipsSum += t.getComputeDemand();
                    } else {
                        computeSum += t.getComputeDemand();
                    }
                }
                if (t.getStorageDemandMb() != null) storageSum += t.getStorageDemandMb();
                if (t.getTransportDemandMbps() != null) transportSum += t.getTransportDemandMbps();
            }
            vo.setTaskComputeUsage(Math.round(computeSum / 1_000_000_000_000.0 * 100.0) / 100.0);
            vo.setTaskComputeUsageMips(Math.round(computeMipsSum * 100.0) / 100.0);
            vo.setTaskStorageUsage(Math.round(storageSum / 1024.0 * 100.0) / 100.0);
            vo.setTaskTransportUsage(Math.round(transportSum * 100.0) / 100.0);
        }

        return vo;
    }

    @Override
    public PreviewWrapper<TaskPreviewVO> getTasksByTrainId(Integer trainId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByTrainId(trainId);
        List<TaskPreviewVO> items = tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
        // 从 resource_summary 表获取该列车的总算力，按任务计算类型（MIPS/FLOPS）选取对应总量计算资源占比
        ResourceSummary summary = resourceSummaryMapper.selectByLayer("train", trainId);
        if (summary != null) {
            for (int i = 0; i < tasks.size(); i++) {
                TaskInfo t = tasks.get(i);
                if (t.getComputeDemand() != null) {
                    boolean isMips = t.getComputeType() != null && t.getComputeType().toLowerCase().contains("mips");
                    Double total = isMips ? summary.getComputeMipsTotal() : summary.getComputeFlopsTotal();
                    if (total != null && total > 0) {
                        items.get(i).setComputeResourceRatio(Math.round(t.getComputeDemand() / total * 100.0 * 100.0) / 100.0);
                    }
                }
            }
        }
        // 按任务计算资源占比从大到小排序
        items.sort(Comparator.comparing(TaskPreviewVO::getComputeResourceRatio, Comparator.nullsLast(Comparator.reverseOrder())));
        return new PreviewWrapper<>(items, summary);
    }

    @Override
    public List<CarriagePreviewVO> getCarriagesByTrainId(Integer trainId) {
        List<CarriageInfo> carriages = carriageInfoMapper.selectCarriagesByTrainId(trainId);
        for (CarriageInfo carriage : carriages) {
            carriage.setDeviceCount(computeNodesMapper.countDevicesByCarriageId(carriage.getId()));
        }
        return carriages.stream().map(TransformUtil::toCarriagePreview).collect(Collectors.toList());
    }

    @Override
    public List<CarriageResourceVO> getCarriageResourcesByTrainId(Integer trainId) {
        List<ResourceSummary> summaries = resourceSummaryMapper.selectCarriagesByTrainId(trainId);
        List<CarriageResourceVO> result = new ArrayList<>();
        if (summaries == null) return result;
        for (ResourceSummary s : summaries) {
            CarriageResourceVO vo = new CarriageResourceVO();
            vo.setCarriageId(s.getLayerId());
            vo.setCarriageName(s.getLayerName());
            // 计算力（FLOPS）
            vo.setComputeFlopsTotal(s.getComputeFlopsTotal());
            vo.setComputeFlopsUsed(s.getComputeFlopsUsed());
            vo.setComputeFlopsRatio(s.getComputeFlopsRatio());
            // 计算力（MIPS）
            vo.setComputeMipsTotal(s.getComputeMipsTotal());
            vo.setComputeMipsUsed(s.getComputeMipsUsed());
            vo.setComputeMipsRatio(s.getComputeMipsRatio());
            // 运载力
            vo.setTransportTotal(s.getTransportTotal());
            vo.setTransportUsed(s.getTransportUsed());
            vo.setTransportRatio(s.getTransportRatio());
            // 存储力
            vo.setStorageTotal(s.getStorageTotal());
            vo.setStorageUsed(s.getStorageUsed());
            vo.setStorageRatio(s.getStorageRatio());
            // 统计
            vo.setDeviceCount(s.getDeviceCount());
            vo.setTaskCount(s.getTaskCount());
            result.add(vo);
        }
        return result;
    }

    @Override
    public TrainViewVO getTrainView(Integer trainId) {
        TrainInfo train = trainInfoMapper.selectById(trainId);
        if (train == null) return null;

        TrainViewVO view = new TrainViewVO();
        view.setTrainId(train.getId());
        view.setTrainCode(train.getTrainCode());
        view.setTrainNumber(train.getTrainNumber() != null ? "G" + train.getTrainNumber() : null);
        view.setCarCount(carriageInfoMapper.countCarriagesByTrainId(trainId));

        List<CarriageViewVO> carriages = carriageInfoMapper.selectCarriageViewByTrainId(trainId);
        view.setCarriages(carriages);

        return view;
    }
}
