package org.cpnvisualsystem.entity.vo;

import lombok.Data;
import java.util.List;

@Data
public class TrainViewVO {
    private Integer trainId;
    private String trainCode;
    private String trainNumber;
    private Integer carCount;
    private List<CarriageViewVO> carriages;
}