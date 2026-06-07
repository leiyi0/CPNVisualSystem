package org.cpnvisualsystem.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceInfoVO {
    private Integer deviceId;
    private String deviceName;
    private String deviceNameCn;
    private String deviceTag;
    private String ip;
    private String osVersion;
    private String kernelVersion;
    private String cpuArch;
    private String cpuModel;
    private Integer cpuCores;
    private Double cpuBaseFreq;
    private Double memoryTotalMb;
    private String memoryTotalUnit = "MB";
    private Double storageTotalMb;
    private String storageTotalUnit = "MB";
    private Integer carriageId;
    private Integer hasGpu;
    private String gpuModel;
    private Double gpuMemTotalMb;
    private String gpuMemTotalUnit = "MB";
    private String status;
    private Date updatedAt;
}
