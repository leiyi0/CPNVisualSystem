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
}
