package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class CarriageInfoVO {
    private String carriageName;
    private String type;
    private String status;
    private Integer deviceCount;
    private Double longitude;
    private Double latitude;
}
