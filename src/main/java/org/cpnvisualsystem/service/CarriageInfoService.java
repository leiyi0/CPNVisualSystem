package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.vo.CarriageInfoVO;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;
import org.cpnvisualsystem.entity.vo.PreviewWrapper;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;

import java.util.List;

public interface CarriageInfoService {
    CarriageInfoVO getById(Integer id);
    PreviewWrapper<TaskPreviewVO> getTasksByCarriageId(Integer carriageId);
    List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId);

    CarriageViewVO getCarriageView(Integer carriageId);
}
