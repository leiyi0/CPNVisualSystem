package org.cpnvisualsystem.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 任务信息概览对象
 */
@Data
public class TaskInfoVo {
    private Long id;
    private String taskId;
    private String taskName;
    private String state;
    private String priorityLevel;
    private String dataType;
    private Double computeDemand;
    private Integer sourceDevice;
    private Integer sourceCarriage;
    private String sourceDeviceTag;
    private List<String> targetDeviceTag;
    private Double storageDemand;
    private Double transportDemand;
    private Date createdAt;
}
