package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Override
    public List<ComputeNodes> getDevicesByCarriageId(Integer carriageId) {
        return computeNodesMapper.selectDevicesByCarriageId(carriageId);
    }

    @Override
    public ComputeNodes getDeviceById(Integer deviceId) {
        return computeNodesMapper.selectById(deviceId);
    }

    @Override
    public List<TaskInfo> getTasksByDeviceId(Integer deviceId) {
        return taskInfoMapper.selectTasksByDeviceId(deviceId);
    }
}