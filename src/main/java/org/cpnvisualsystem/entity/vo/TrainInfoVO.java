package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class TrainInfoVO {
    private String trainId;
    private Double longitude;
    private Double latitude;
    private Double speed;
    private String status;
    private Integer carriageCount;
    private String trainNumber;
}
