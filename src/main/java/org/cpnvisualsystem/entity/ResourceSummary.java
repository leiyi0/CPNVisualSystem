package org.cpnvisualsystem.entity;

import lombok.Data;

import java.util.Date;

/**
 * 资源汇总实体，对应 slwl.resource_summary 表
 * 按 cluster / train / carriage / device / overall 各层级汇总算力资源
 */
@Data
public class ResourceSummary {
    private Long id;
    private String layerType;
    private Integer layerId;
    private String parentType;
    private Integer parentId;
    private String layerName;
    private Integer trainCount;
    private Integer carriageCount;
    private Integer deviceCount;
    private Integer taskCount;
    private Double computeFlopsTotal;
    private Double computeFlopsUsed;
    private Double computeFlopsFree;
    private Double computeFlopsRatio;
    private Double computeMipsTotal;
    private Double computeMipsUsed;
    private Double computeMipsFree;
    private Double computeMipsRatio;
    private Double storageTotal;
    private Double storageUsed;
    private Double storageFree;
    private Double storageRatio;
    private Double transportTotal;
    private Double transportUsed;
    private Double transportFree;
    private Double transportRatio;
    private Date updatedAt;

    // ========== 单位字段（非数据库字段，仅用于接口返回） ==========
    private String computeFlopsTotalUnit = "TFLOPS";
    private String computeFlopsUsedUnit = "TFLOPS";
    private String computeFlopsFreeUnit = "TFLOPS";
    private String computeFlopsRatioUnit = "%";
    private String computeMipsTotalUnit = "MIPS";
    private String computeMipsUsedUnit = "MIPS";
    private String computeMipsFreeUnit = "MIPS";
    private String computeMipsRatioUnit = "%";
    private String storageTotalUnit = "MB";
    private String storageUsedUnit = "MB";
    private String storageFreeUnit = "MB";
    private String storageRatioUnit = "%";
    private String transportTotalUnit = "Gbps";
    private String transportUsedUnit = "Gbps";
    private String transportFreeUnit = "Gbps";
    private String transportRatioUnit = "%";
}
