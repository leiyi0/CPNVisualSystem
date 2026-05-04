package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.TrainViewVO;
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

    @Override
    public TrainViewVO getTrainView(Integer trainId) {
        TrainInfo train = trainInfoMapper.selectById(trainId);
        if (train == null) return null;

        TrainViewVO view = new TrainViewVO();
        view.setTrainId(String.valueOf(train.getTrainNumber()));
        view.setCarCount(train.getCarriageCount());

        // 获取该列车下所有车厢的视图数据
        List<CarriageViewVO> carriages = carriageInfoMapper.selectCarriageViewByTrainId(trainId);
        view.setCarriages(carriages);

        return view;
    }
}