package org.cpnvisualsystem.entity;

import lombok.Data;

@Data
public class TrainInfo {
    private Integer id;
    private Integer clusterId;
    private String position;
    private Double speed;
    private Integer carriageCount;
    private Integer trainNumber;
}