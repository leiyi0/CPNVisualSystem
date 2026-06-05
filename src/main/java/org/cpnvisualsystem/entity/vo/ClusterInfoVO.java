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
}
