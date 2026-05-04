package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;

import java.util.List;

public interface CarriageInfoService {
    CarriageInfo getById(Integer id);
    List<TaskInfo> getTasksByCarriageId(Integer carriageId);
    List<ComputeNodes> getDevicesByCarriageId(Integer carriageId);

    CarriageViewVO getCarriageView(Integer carriageId);
}