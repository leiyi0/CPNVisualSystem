package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;
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

    @Override
    public List<TaskInfoVo> getTasksByPage(Integer pageNum, Integer pageSize, String state, String taskName) {
        if (pageNum == null || pageNum < 1) pageNum = 1;
        if (pageSize == null || pageSize < 1) pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        List<TaskInfo> taskInfos = taskInfoMapper.selectTasksByPage(offset, pageSize, state, taskName);
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
            t.setSourceDevice(info.getSourceDevice());
            t.setSourceCarriage(info.getSourceCarriage());
            t.setCreatedAt(info.getCreatedAt());
            t.setStorageDemand(info.getStorageDemandMb());
            t.setTransportDemand(info.getTransportDemandMbps());
            t.setSourceDeviceTag(info.getSourceDeviceTag());
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
    public Integer countTasksByFilter(String state, String taskName) {
        return taskInfoMapper.countTasksByFilter(state, taskName);
    }
}
