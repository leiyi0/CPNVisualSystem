package org.cpnvisualsystem.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * 集群故障状态 VO
 */
@Data
public class ClusterFaultVO {
    /** 集群状态 */
    private String clusterStatus;

    /** 列车总数 */
    private Integer totalTrains;

    /** 设备总数 */
    private Integer totalDevices;

    /** 任务总数 */
    private Integer totalTasks;

    /** 列车状态: {运行中: X, 停靠中: X, 异常: X} */
    private Map<String, Integer> trainStatus;

    /** 车厢状态: {运行中: X, 未通电: X, 异常: X} */
    private Map<String, Integer> carriageStatus;

    /** 设备状态: {正常: X, 离线: X, 告警: X} */
    private Map<String, Integer> deviceStatus;

    /** 任务状态: {运行中: X, 失败: X, 已完成: X} */
    private Map<String, Integer> taskStatus;
}
