package org.cpnvisualsystem.entity;

import lombok.Data;
import java.util.Date;

@Data
public class NodeMetricsInfo {
    private Integer id;
    private Integer nodeId;
    private Float cpuUsageCore;
    private Float cpuUsagePercent;
    private Float memoryUsagePercent;
    private Long memoryUsedBytes;
    private Float memoryFreqCurrentMhz;
    private Float gpuUsagePercent;
    private Long gpuMemUsedBytes;
    private Long bandwidthInBps;
    private Long bandwidthOutBps;
    private Float bandwidthInPercent;
    private Float bandwidthOutPercent;
    private Float netRttAvgMs;
    private Float netPacketLossPercent;
    private Date createdAt;
}

