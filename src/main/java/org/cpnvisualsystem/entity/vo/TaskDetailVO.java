package org.cpnvisualsystem.entity.vo;

import lombok.Data;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.NodeMetricsInfo;
import java.util.List;

@Data
public class TaskDetailVO {
    private TaskInfo taskInfo;
    private NodeMetricsInfo sourceDeviceInfo;
    private List<NodeMetricsInfo> targetDeviceInfoList;

    // 资源占比
    private Double computePowerRatio;
    private String computePowerRatioUnit = "%";
    private Double storagePowerRatio;
    private String storagePowerRatioUnit = "%";
    private Double transportPowerRatio;
    private String transportPowerRatioUnit = "%";
}
