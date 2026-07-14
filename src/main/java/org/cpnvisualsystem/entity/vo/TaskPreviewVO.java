package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class TaskPreviewVO {
    private String taskId;
    private String taskName;
    private String type;
    private String priority;
    private String status;
    private String computeRequirement;
    private String runningDevice;
    private String matchStrategy;
    private Double computeResourceRatio;
    private String computeResourceRatioUnit = "%";

    // 车厢和设备ID（用于关联 /api/train/carriages/{id} 等接口）
    private Integer sourceCarriage;
    private Integer sourceDevice;
    private String targetDeviceIds;
}
