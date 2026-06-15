package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class CarriageInfoVO {
    private Integer carriageId;
    private String carriageCode;
    private Integer clusterId;
    private String type;
    private String status;
    private Integer deviceCount;
    private Double longitude;
    private Double latitude;

    // 支持的设备总数
    private Integer supportedDeviceCount;

    // 静态算力总量
    private Double totalComputePower;
    private String totalComputePowerUnit = "MFLOPS";
    private Double totalComputePowerMips;
    private String totalComputePowerMipsUnit = "MIPS";
    private Double totalStoragePower;
    private String totalStoragePowerUnit = "MB";
    private Double totalTransportPower;
    private String totalTransportPowerUnit = "Gbps";

    // 任务汇总
    private Integer taskCount;
    private Double taskComputeUsage;
    private String taskComputeUsageUnit = "MFLOPS";
    private Double taskStorageUsage;
    private String taskStorageUsageUnit = "MB";
    private Double taskTransportUsage;
    private String taskTransportUsageUnit = "Mbps";
}
