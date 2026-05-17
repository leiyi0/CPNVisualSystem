package org.cpnvisualsystem.entity;

import lombok.Data;

@Data
public class ClusterInfo {
    private Integer id;
    private String clusterCode;
    private String coverageArea;
    private Double longitude;
    private Double latitude;
    private Double mapLeft;
    private Double mapTop;
    private Integer trainCount;
    private String status;
}