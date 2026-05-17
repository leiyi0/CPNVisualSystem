package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class DevicePreviewVO {
    private String deviceId;
    private String deviceName;
    private String type;
    private String status;
    private String thumbnail;
}
