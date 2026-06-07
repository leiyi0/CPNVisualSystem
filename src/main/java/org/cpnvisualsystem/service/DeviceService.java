package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.vo.DeviceInfoVO;
import org.cpnvisualsystem.entity.vo.DevicePreviewVO;

import java.util.List;

public interface DeviceService {
    List<DevicePreviewVO> getDevicesByCarriageId(Integer carriageId);
    DeviceInfoVO getDeviceById(Integer deviceId);
    List<org.cpnvisualsystem.entity.vo.TaskPreviewVO> getTasksByDeviceId(Integer deviceId);
}
