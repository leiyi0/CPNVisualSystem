package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.CarriageInfoVO;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CarriageInfoVO getById(Integer id) {
        CarriageInfo carriage = carriageInfoMapper.selectById(id);
        if (carriage == null) return null;
        carriage.setDeviceCount(computeNodesMapper.countDevicesByCarriageId(id));
        return TransformUtil.toCarriageInfo(carriage);
    }

    @Override
    public List<TaskPreviewVO> getTasksByCarriageId(Integer carriageId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByCarriageId(carriageId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
    }

    @Override
    public List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId) {
        List<ComputeNodes> devices = computeNodesMapper.selectDevicesByCarriageId(carriageId);
        return devices.stream().map(TransformUtil::toDevicePreview).collect(Collectors.toList());
    }

    @Override
    public CarriageViewVO getCarriageView(Integer carriageId) {
        return carriageInfoMapper.selectCarriageViewByCarriageId(carriageId);
    }
}
