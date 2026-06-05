package org.cpnvisualsystem.entity.vo;

import lombok.Data;

@Data
public class TrainPreviewVO {
    private Integer trainId;
    private String trainCode;
    private Double longitude;
    private Double latitude;
}
