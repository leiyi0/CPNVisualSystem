package org.cpnvisualsystem.entity.vo;

import lombok.Data;

import java.util.Map;

/**
 * 任务统计 VO，按多种维度分组统计
 */
@Data
public class TaskStatsVO {
    /** 任务总数 */
    private Integer totalCount;

    /** 按任务状态: {运行中: X, 失败: X, 已完成: X} */
    private Map<String, Integer> byState;

    /** 按任务层级: {集群级: X, 列车级: X, 车辆级: X} */
    private Map<String, Integer> byLevel;

    /** 按任务类型: {控制诊断: X, 旅客服务: X, 辅助监控: X, 行车安全: X} */
    private Map<String, Integer> byType;

    /** 按优先级: {安全关键级: X, 运行关键级: X, 非关键级: X} */
    private Map<String, Integer> byPriority;

    /** 按匹配策略: {强绑定: X, 弱绑定: X, 池化匹配: X} */
    private Map<String, Integer> byMatchStrategy;
}
