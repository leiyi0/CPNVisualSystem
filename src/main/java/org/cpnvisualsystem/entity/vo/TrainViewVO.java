package org.cpnvisualsystem.entity.vo;

import lombok.Data;
import java.util.List;

@Data
public class TrainViewVO {
    private String trainId;
    private Integer carCount;
    private List<CarriageViewVO> carriages;
}