package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskInfo {
    private Long id; // bigint
    private String taskId; // varchar
    private String taskName; // varchar
    private Integer executionSeq; // int
    private String state; // varchar
    private String priorityLevel; // varchar
    private String dataType; // varchar
    private Double computeDemand; // bigint
    private String computeType; // varchar
    private Float dataSizeMb; // float
    private Double storageDemandMb; // double
    private Double transportDemandMbps; // double
    private Float maxLatencyMs; // float
    private Integer sourceDevice; // int
    private String sourceDeviceTag;
    private Integer sourceCarriage; // int
    private String proximityConstraint; // varchar
    private Boolean allowDegradation; // tinyint -> Boolean
    private String currentNodeId; // varchar
    private String targetDeviceTags; // varchar
    private String targetDeviceIds; // varchar
    private String targetDeviceTypes; // varchar
    private String targetVehicleIds; // varchar
    private String targetDeviceIps; // varchar
    private String targetDeviceInfoJson; // json
    private Integer retryCount; // int
    private Long latestHistoryId; // bigint
    private String taskSpecJson; // text
    private Date createdAt; // timestamp
    private Date updatedAt; // timestamp
    private String allocationResult; // json
}