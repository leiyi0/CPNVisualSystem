package org.cpnvisualsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalOverview {
    private Integer clusterCount;
    private Integer trainCount;
}
