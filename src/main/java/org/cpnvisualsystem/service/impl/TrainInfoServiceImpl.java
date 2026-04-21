package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.TrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainInfoServiceImpl implements TrainInfoService {

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Override
    public TrainInfo getTrainById(Integer trainId) {
        return trainInfoMapper.selectById(trainId);
    }

    @Override
    public List<TaskInfo> getTasksByTrainId(Integer trainId) {
        return taskInfoMapper.selectTasksByTrainId(trainId);
    }

    @Override
    public List<CarriageInfo> getCarriagesByTrainId(Integer trainId) {
        return carriageInfoMapper.selectCarriagesByTrainId(trainId);
    }
}