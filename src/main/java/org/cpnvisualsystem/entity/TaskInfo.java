package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskInfo {
    private Long id;
    private String taskId;
    private String taskName;
    private String state;
    private String priorityLevel;
    private String dataType;
    private Long computeDemand;
    private Integer sourceDevice;
    private Integer sourceCarriage;
    private Date createdAt;
}