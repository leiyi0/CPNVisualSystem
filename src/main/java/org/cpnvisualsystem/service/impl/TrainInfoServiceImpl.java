package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
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

    @Override
    public TrainInfoVO getTrainById(Integer trainId) {
        TrainInfo train = trainInfoMapper.selectById(trainId);
        if (train == null) return null;
        train.setCarriageCount(carriageInfoMapper.countCarriagesByTrainId(trainId));
        return TransformUtil.toTrainInfo(train);
    }

    @Override
    public List<TaskPreviewVO> getTasksByTrainId(Integer trainId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByTrainId(trainId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
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
        view.setTrainId(String.valueOf(train.getTrainNumber()));
        view.setCarCount(carriageInfoMapper.countCarriagesByTrainId(trainId));

        List<CarriageViewVO> carriages = carriageInfoMapper.selectCarriageViewByTrainId(trainId);
        view.setCarriages(carriages);

        return view;
    }
}
