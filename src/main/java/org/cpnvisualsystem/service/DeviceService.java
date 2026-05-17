package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;

import java.util.List;

public interface DeviceService {
    List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId);
    ComputeNodes getDeviceById(Integer deviceId);
    List<org.cpnvisualsystem.entity.vo.TaskPreviewVO> getTasksByDeviceId(Integer deviceId);
}
