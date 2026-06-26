package org.cpnvisualsystem.entity.vo;

import lombok.Data;

/**
 * 车厢资源统计 VO，用于列车下车厢粒度的算力/存储/运载资源情况
 */
@Data
public class CarriageResourceVO {
    private Integer carriageId;
    private String carriageName;

    // ---- 计算力（FLOPS）----
    private Double computeFlopsTotal;
    private Double computeFlopsUsed;
    private Double computeFlopsRatio;

    // ---- 计算力（MIPS）----
    private Double computeMipsTotal;
    private Double computeMipsUsed;
    private Double computeMipsRatio;

    // ---- 运载力 ----
    private Double transportTotal;
    private Double transportUsed;
    private Double transportRatio;

    // ---- 存储力 ----
    private Double storageTotal;
    private Double storageUsed;
    private Double storageRatio;

    // ---- 统计 ----
    private Integer deviceCount;
    private Integer taskCount;
}
