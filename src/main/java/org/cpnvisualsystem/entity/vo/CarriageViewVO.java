package org.cpnvisualsystem.entity.vo;

import lombok.Data;
import java.util.List;

@Data
public class CarriageViewVO {
    private Integer carNo;        // 车辆编号
    private Integer deviceCount;  // 搭载设备数
    private List<DeviceKV> devices; // 设备列表 {deviceId, deviceName}
}