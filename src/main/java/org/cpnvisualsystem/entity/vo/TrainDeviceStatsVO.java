package org.cpnvisualsystem.entity.vo;

import lombok.Data;

/**
 * 列车设备统计 VO —— 每列车的接入设备总数与空余设备数
 */
@Data
public class TrainDeviceStatsVO {
    private Integer trainId;
    private String trainCode;
    private Integer trainNumber;

    /** 接入设备总数 */
    private Integer deviceCount;

    /** 空余设备数（非正常状态的设备数量） */
    private Integer spareDeviceCount;
}
