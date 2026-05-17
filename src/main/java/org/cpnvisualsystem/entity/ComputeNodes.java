package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ComputeNodes {
    private Integer id;
    private String deviceTag;
    private String ip;
    private String osVersion;
    private String kernelVersion;
    private String cpuArch;
    private String cpuModel;
    private Integer cpuCores;
    private Double cpuBaseFreq;
    private String memoryTotalBytes;
    private String storageTotalBytes;
    private Integer hasGpu;
    private String gpuModel;
    private String gpuMemTotalBytes;
    private String status;
    private Date updatedAt;
}
