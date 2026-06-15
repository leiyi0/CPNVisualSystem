package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.CarriageInfoVO;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.StaticPowerMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarriageInfoServiceImpl implements CarriageInfoService {

    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private StaticPowerService staticPowerService;

    @Autowired
    private StaticPowerMapper staticPowerMapper;

    @Override
    public CarriageInfoVO getById(Integer id) {
        CarriageInfo carriage = carriageInfoMapper.selectById(id);
        if (carriage == null) return null;
        carriage.setDeviceCount(computeNodesMapper.countDevicesByCarriageId(id));
        CarriageInfoVO vo = TransformUtil.toCarriageInfo(carriage);

        // 支持的设备总数（车厢可容纳的最大设备数，先取已搭载的设备数）
        vo.setSupportedDeviceCount(computeNodesMapper.countDevicesByCarriageId(id));

        // 填充静态算力总量
        StaticPowerInfo staticPower = staticPowerService.getStaticPowerByCarriageId(id);
        if (staticPower != null) {
            vo.setTotalComputePower(staticPower.getComputerPower() != null ?
                    Math.round(staticPower.getComputerPower() * 1_000_000.0 * 100.0) / 100.0 : null);
            vo.setTotalComputePowerMips(staticPower.getComputerPowerMips());
            vo.setTotalStoragePower(staticPower.getStoragePower() != null ?
                    Math.round(staticPower.getStoragePower() * 1024.0 * 100.0) / 100.0 : null);
            vo.setTotalTransportPower(staticPower.getTransportPower());
        }

        // 填充任务汇总
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByCarriageId(id);
        if (tasks != null && !tasks.isEmpty()) {
            vo.setTaskCount(tasks.size());
            double computeSum = 0, storageSum = 0, transportSum = 0;
            for (TaskInfo t : tasks) {
                if (t.getComputeDemand() != null) computeSum += t.getComputeDemand();
                if (t.getStorageDemandMb() != null) storageSum += t.getStorageDemandMb();
                if (t.getTransportDemandMbps() != null) transportSum += t.getTransportDemandMbps();
            }
            vo.setTaskComputeUsage(Math.round(computeSum * 1_000_000.0 * 100.0) / 100.0);
            vo.setTaskStorageUsage(Math.round(storageSum * 100.0) / 100.0);
            vo.setTaskTransportUsage(Math.round(transportSum * 100.0) / 100.0);
        }

        return vo;
    }

    @Override
    public List<TaskPreviewVO> getTasksByCarriageId(Integer carriageId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByCarriageId(carriageId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
    }

    @Override
    public List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId) {
        List<ComputeNodes> devices = computeNodesMapper.selectDevicesByCarriageId(carriageId);
        List<DevicePreviewVO> result = new ArrayList<>();
        for (ComputeNodes node : devices) {
            DevicePreviewVO vo = TransformUtil.toDevicePreview(node);

            // 静态算力
            StaticPowerInfo sp = staticPowerMapper.getStaticPowerInfo(node.getId());
            if (sp != null) {
                if (sp.getComputerPower() != null) {
                    vo.setComputerPower(Math.round(sp.getComputerPower() / 1_000_000.0 * 100.0) / 100.0);
                }
                if (sp.getComputerPowerMips() != null) {
                    vo.setComputerPowerMips(sp.getComputerPowerMips());
                }
                if (sp.getStoragePower() != null) {
                    vo.setStoragePower(Math.round(sp.getStoragePower() * 100.0) / 100.0);
                }
                if (sp.getTransportPower() != null) {
                    vo.setTransportPower(Math.round(sp.getTransportPower() / 1000.0 * 100.0) / 100.0);
                }
            }

            // 任务汇总
            List<TaskInfo> tasks = taskInfoMapper.selectTasksByDeviceId(node.getId());
            if (tasks != null && !tasks.isEmpty()) {
                vo.setTaskCount(tasks.size());
                double computeSum = 0, storageSum = 0, transportSum = 0;
                for (TaskInfo t : tasks) {
                    if (t.getComputeDemand() != null) computeSum += t.getComputeDemand();
                    if (t.getStorageDemandMb() != null) storageSum += t.getStorageDemandMb();
                    if (t.getTransportDemandMbps() != null) transportSum += t.getTransportDemandMbps();
                }
                vo.setTaskComputeUsage(Math.round(computeSum * 1_000_000.0 * 100.0) / 100.0);
                vo.setTaskStorageUsage(Math.round(storageSum * 100.0) / 100.0);
                vo.setTaskTransportUsage(Math.round(transportSum * 100.0) / 100.0);
            }

            result.add(vo);
        }
        return result;
    }

    @Override
    public CarriageViewVO getCarriageView(Integer carriageId) {
        return carriageInfoMapper.selectCarriageViewByCarriageId(carriageId);
    }
}
