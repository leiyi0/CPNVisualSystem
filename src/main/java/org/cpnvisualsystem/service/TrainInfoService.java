package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.vo.CarriagePreviewVO;
import org.cpnvisualsystem.entity.vo.CarriageResourceVO;
import org.cpnvisualsystem.entity.vo.PreviewWrapper;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainDeviceStatsVO;
import org.cpnvisualsystem.entity.vo.TrainFaultVO;
import org.cpnvisualsystem.entity.vo.TrainInfoVO;
import org.cpnvisualsystem.entity.vo.TrainViewVO;

import java.util.List;

public interface TrainInfoService {
    TrainInfoVO getTrainById(Integer trainId);
    PreviewWrapper<TaskPreviewVO> getTasksByTrainId(Integer trainId);
    List<CarriagePreviewVO> getCarriagesByTrainId(Integer trainId);
    List<CarriageResourceVO> getCarriageResourcesByTrainId(Integer trainId);
    TrainViewVO getTrainView(Integer trainId);
    TrainDeviceStatsVO getDeviceStatsByTrainId(Integer trainId);
    TrainFaultVO getTrainFault(Integer trainId);
}
