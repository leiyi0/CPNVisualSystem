package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.TrainViewVO;

import java.util.List;

public interface TrainInfoService {
    TrainInfo getTrainById(Integer trainId);
    List<TaskInfo> getTasksByTrainId(Integer trainId);
    List<CarriageInfo> getCarriagesByTrainId(Integer trainId);

    TrainViewVO getTrainView(Integer trainId);
}