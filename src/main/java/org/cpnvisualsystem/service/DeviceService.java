package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TaskInfo;

import java.util.List;

public interface DeviceService {
    List<ComputeNodes> getDevicesByCarriageId(Integer carriageId);
    ComputeNodes getDeviceById(Integer deviceId);
    List<TaskInfo> getTasksByDeviceId(Integer deviceId);
}