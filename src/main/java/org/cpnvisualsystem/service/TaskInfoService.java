package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.TaskExecuteLog;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskDetailVO;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;
import org.cpnvisualsystem.entity.vo.TaskStatsVO;

import java.util.List;

public interface TaskInfoService {
    List<TaskInfoVo> getTasksByPage(Integer pageNum, Integer pageSize, String state, String taskName, Integer deviceId);

    Integer countTasksByFilter(String state, String taskName, Integer deviceId);

    List<TaskExecuteLog> getLogsByTaskId(Integer taskId);

    TaskDetailVO getTaskDetailById(Long id);

    TaskStatsVO getTaskStats();
}
