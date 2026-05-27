package org.cpnvisualsystem.entity;

import lombok.Data;

@Data
public class TotalOverview {
    private Integer clusterCount;
    private Integer trainCount;
    private Double onlineRate;
}
