package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class DevicePreviewVO {
    private Integer deviceId;
    private String deviceName;
    private String type;
    private String status;
    private String thumbnail;

    // 静态算力
    private Double computerPower;
    private String computerPowerUnit = "MFLOPS";
    private Double computerPowerMips;
    private String computerPowerMipsUnit = "MIPS";
    private Double storagePower;
    private String storagePowerUnit = "MB";
    private Double transportPower;
    private String transportPowerUnit = "Gbps";

    // 任务汇总
    private Integer taskCount;
    private Double taskComputeUsage;
    private String taskComputeUsageUnit = "MFLOPS";
    private Double taskStorageUsage;
    private String taskStorageUsageUnit = "MB";
    private Double taskTransportUsage;
    private String taskTransportUsageUnit = "Mbps";
}
