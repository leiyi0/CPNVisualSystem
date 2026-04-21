package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarriageInfoServiceImpl implements CarriageInfoService {

    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Override
    public CarriageInfo getById(Integer id) {
        return carriageInfoMapper.selectById(id);
    }

    @Override
    public List<TaskInfo> getTasksByCarriageId(Integer carriageId) {
        return taskInfoMapper.selectTasksByCarriageId(carriageId);
    }

    @Override
    public List<ComputeNodes> getDevicesByCarriageId(Integer carriageId) {
        // 调用上一步写好的桥接查询 SQL
        return computeNodesMapper.selectDevicesByCarriageId(carriageId);
    }
}