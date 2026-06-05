package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class CarriageInfoVO {
    private Integer carriageId;
    private String carriageCode;
    private Integer clusterId;
    private String type;
    private String status;
    private Integer deviceCount;
    private Double longitude;
    private Double latitude;
}
