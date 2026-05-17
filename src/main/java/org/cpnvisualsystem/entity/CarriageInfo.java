package org.cpnvisualsystem.entity;

import lombok.Data;

@Data
public class CarriageInfo {
    private Integer id;
    private Integer clusterId;
    private Integer trainId;
    private String carriageCode;
    private String type;
    private String position;
    private Integer deviceCount;
    private String status;
}