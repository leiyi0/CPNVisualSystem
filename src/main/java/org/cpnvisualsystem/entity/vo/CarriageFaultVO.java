package org.cpnvisualsystem.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * 车厢故障状态 VO
 */
@Data
public class CarriageFaultVO {
    /** 设备总数 */
    private Integer totalDevices;

    /** 设备状态: {正常: X, 离线: X, 告警: X} */
    private Map<String, Integer> deviceStatus;

    /** 任务状态: {运行中: X, 失败: X, 已完成: X} */
    private Map<String, Integer> taskStatus;
}
