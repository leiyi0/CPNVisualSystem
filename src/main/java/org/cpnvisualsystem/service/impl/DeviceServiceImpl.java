package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.DeviceInfoVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.StaticPowerMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.DeviceService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private StaticPowerMapper staticPowerMapper;

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
    public DeviceInfoVO getDeviceById(Integer deviceId) {
        ComputeNodes node = computeNodesMapper.selectById(deviceId);
        if (node == null) return null;

        DeviceInfoVO vo = new DeviceInfoVO();
        vo.setDeviceId(node.getId());
        vo.setDeviceName(node.getName());
        vo.setDeviceNameCn(node.getDeviceNameCn());
        vo.setDeviceTag(node.getDeviceTag());
        vo.setIp(node.getIp());
        vo.setOsVersion(node.getOsVersion());
        vo.setKernelVersion(node.getKernelVersion());
        vo.setCpuArch(node.getCpuArch());
        vo.setCpuModel(node.getCpuModel());
        vo.setCpuCores(node.getCpuCores());
        vo.setCpuBaseFreq(node.getCpuBaseFreq());
        vo.setMemoryTotalMb(bytesToMb(node.getMemoryTotalBytes()));
        vo.setStorageTotalMb(bytesToMb(node.getStorageTotalBytes()));
        vo.setCarriageId(node.getCarriageId());
        vo.setHasGpu(node.getHasGpu());
        vo.setGpuModel(node.getGpuModel());
        vo.setGpuMemTotalMb(bytesToMb(node.getGpuMemTotalBytes()));
        vo.setStatus(node.getStatus());
        vo.setUpdatedAt(node.getUpdatedAt());
        return vo;
    }

    private Double bytesToMb(String bytesStr) {
        if (bytesStr == null || bytesStr.isEmpty()) return null;
        try {
            double bytes = Double.parseDouble(bytesStr);
            return Math.round(bytes / (1024.0 * 1024.0) * 100.0) / 100.0;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public List<TaskPreviewVO> getTasksByDeviceId(Integer deviceId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByDeviceId(deviceId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
    }
}
