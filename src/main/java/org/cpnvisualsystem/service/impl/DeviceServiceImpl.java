package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.DeviceInfoVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.DeviceService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId) {
        List<ComputeNodes> devices = computeNodesMapper.selectDevicesByCarriageId(carriageId);
        return devices.stream().map(TransformUtil::toDevicePreview).collect(Collectors.toList());
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
