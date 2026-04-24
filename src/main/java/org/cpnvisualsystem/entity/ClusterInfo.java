package org.cpnvisualsystem.entity;

import lombok.Data;

@Data
public class ClusterInfo {
    private Integer id;
    private String clusterCode;
    private String coverageArea;
    private Integer trainCount;
}