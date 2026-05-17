package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
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
    public ComputeNodes getDeviceById(Integer deviceId) {
        return computeNodesMapper.selectById(deviceId);
    }

    @Override
    public List<TaskPreviewVO> getTasksByDeviceId(Integer deviceId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByDeviceId(deviceId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
    }
}
