package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class TrainInfoVO {
    private Integer trainId;
    private String trainCode;
    private Double longitude;
    private Double latitude;
    private Double speed;
    private String status;
    private Integer carriageCount;
    private String trainNumber;

    // 静态算力总量
    private Double totalComputePower;
    private String totalComputePowerUnit = "TFLOPS";
    private Double totalComputePowerMips;
    private String totalComputePowerMipsUnit = "MIPS";
    private Double totalStoragePower;
    private String totalStoragePowerUnit = "GB";
    private Double totalTransportPower;
    private String totalTransportPowerUnit = "Gbps";

    // 任务汇总
    private Integer taskCount;
    private Double taskComputeUsage;
    private String taskComputeUsageUnit = "TFLOPS";
    private Double taskComputeUsageMips;
    private String taskComputeUsageMipsUnit = "MIPS";
    private Double taskStorageUsage;
    private String taskStorageUsageUnit = "GB";
    private Double taskTransportUsage;
    private String taskTransportUsageUnit = "Mbps";
}
