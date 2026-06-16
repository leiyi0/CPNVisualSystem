package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.CarriagePreviewVO;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainInfoVO;
import org.cpnvisualsystem.entity.vo.TrainViewVO;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.service.TrainInfoService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // 填充任务汇总
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByTrainId(trainId);
        if (tasks != null && !tasks.isEmpty()) {
            vo.setTaskCount(tasks.size());
            double computeSum = 0, storageSum = 0, transportSum = 0;
            for (TaskInfo t : tasks) {
                if (t.getComputeDemand() != null) computeSum += t.getComputeDemand();
                if (t.getStorageDemandMb() != null) storageSum += t.getStorageDemandMb();
                if (t.getTransportDemandMbps() != null) transportSum += t.getTransportDemandMbps();
            }
            vo.setTaskComputeUsage(Math.round(computeSum / 1_000_000_000_000.0 * 100.0) / 100.0);
            vo.setTaskStorageUsage(Math.round(storageSum / 1024.0 * 100.0) / 100.0);
            vo.setTaskTransportUsage(Math.round(transportSum * 100.0) / 100.0);
        }

        return vo;
    }

    @Override
    public List<TaskPreviewVO> getTasksByTrainId(Integer trainId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByTrainId(trainId);
        List<TaskPreviewVO> result = tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
        StaticPowerInfo staticPower = staticPowerService.getStaticPowerByTrainId(trainId);
        if (staticPower != null && staticPower.getComputerPower() != null && staticPower.getComputerPower() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                TaskInfo t = tasks.get(i);
                if (t.getComputeDemand() != null) {
                    double totalPowerFlops = staticPower.getComputerPower() * 1_000_000_000_000.0;
                    result.get(i).setComputeResourceRatio(Math.round(t.getComputeDemand() / totalPowerFlops * 100.0 * 100.0) / 100.0);
                }
            }
        }
        return result;
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
