package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.NodeMetricsInfo;
import org.cpnvisualsystem.entity.TaskExecuteLog;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskDetailVO;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;
import org.cpnvisualsystem.mapper.DynamicPowerMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private DynamicPowerMapper dynamicPowerMapper;

    @Override
    public List<TaskInfoVo> getTasksByPage(Integer pageNum, Integer pageSize, String state, String taskName, Integer deviceId) {
        if (pageNum == null || pageNum < 1) pageNum = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        List<TaskInfo> taskInfos = taskInfoMapper.selectTasksByPage(offset, pageSize, state, taskName, deviceId);
        // 将 TaskInfo 转换为 TaskInfoVo
        List<TaskInfoVo> result = new ArrayList<>();
        if (taskInfos == null || taskInfos.isEmpty()) return result;
        for (TaskInfo info : taskInfos) {
            TaskInfoVo t = new TaskInfoVo();
            t.setId(info.getId());
            t.setTaskId(info.getTaskId());
            t.setTaskName(info.getTaskName());
            // 基本字段
            t.setState(info.getState() == null ? null : info.getState());
            t.setPriorityLevel(info.getPriorityLevel());
            t.setDataType(info.getDataType());
            t.setComputeDemand(info.getComputeDemand());
            t.setComputeType(info.getComputeType());
            t.setSourceDevice(info.getSourceDevice());
            t.setSourceCarriage(info.getSourceCarriage());
            t.setCreatedAt(info.getCreatedAt());
            t.setStorageDemand(info.getStorageDemandMb());
            t.setTransportDemand(info.getTransportDemandMbps());
            t.setSourceDeviceTag(info.getSourceDeviceTag());
            t.setProximityConstraint(info.getProximityConstraint());
            // targetDeviceTags 逗号分隔 -> List<String>
            if (info.getTargetDeviceTags() != null && !info.getTargetDeviceTags().trim().isEmpty()) {
                List<String> tags = Arrays.stream(info.getTargetDeviceTags().split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
                t.setTargetDeviceTag(tags);
            }
            result.add(t);
        }
        return result;
    }

    @Override
    public Integer countTasksByFilter(String state, String taskName, Integer deviceId) {
        return taskInfoMapper.countTasksByFilter(state, taskName, deviceId);
    }

    @Override
    public List<TaskExecuteLog> getLogsByTaskId(Integer taskId) {
        return taskInfoMapper.selectLogsByTaskId(taskId);
    }

    @Override
    public TaskDetailVO getTaskDetailById(Long id) {
        TaskDetailVO vo = new TaskDetailVO();
        TaskInfo taskInfo = taskInfoMapper.selectTaskById(id);
        vo.setTaskInfo(taskInfo);
        // 源设备
        if (taskInfo != null && taskInfo.getSourceDevice() != null) {
            NodeMetricsInfo sourceInfo = dynamicPowerMapper.getLatestNodeMetricsByDeviceId(taskInfo.getSourceDevice());
            vo.setSourceDeviceInfo(sourceInfo);
        }
        // 目标设备
        if (taskInfo != null && taskInfo.getTargetDeviceIds() != null && !taskInfo.getTargetDeviceIds().trim().isEmpty()) {
            String[] idArr = taskInfo.getTargetDeviceIds().split(",");
            List<Integer> deviceIds = new ArrayList<>();
            for (String s : idArr) {
                try {
                    deviceIds.add(Integer.parseInt(s.trim()));
                } catch (Exception ignore) {}
            }
            if (!deviceIds.isEmpty()) {
                List<NodeMetricsInfo> targetInfos = dynamicPowerMapper.getLatestNodeMetricsByDeviceIds(deviceIds);
                vo.setTargetDeviceInfoList(targetInfos);
            }
        }
        return vo;
    }
}
