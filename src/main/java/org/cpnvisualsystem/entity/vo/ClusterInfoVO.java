package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class ClusterInfoVO {
    private Integer clusterId;
    private String clusterCode;
    private String coverageArea;
    private Double longitude;
    private Double latitude;
    private Integer trainCount;
    private String status;

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
