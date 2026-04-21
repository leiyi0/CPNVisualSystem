package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ComputeNodes {
    private Integer id;
    private String deviceTag;
    private String ip;
    private String osVersion;
    private String cpuArch;
    private Integer cpuCores;
    private Integer hasGpu;
    private String status;
    private Date updatedAt;
}
