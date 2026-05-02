package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.TaskExecuteLog;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskDetailVO;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;

import java.util.List;

public interface TaskInfoService {
    List<TaskInfoVo> getTasksByPage(Integer pageNum, Integer pageSize, String state, String taskName);

    Integer countTasksByFilter(String state, String taskName);

    List<TaskExecuteLog> getLogsByTaskId(Integer taskId);

    TaskDetailVO getTaskDetailById(Long id);
}
