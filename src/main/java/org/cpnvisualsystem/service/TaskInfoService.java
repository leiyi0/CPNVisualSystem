package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;

import java.util.List;

public interface TaskInfoService {
    List<TaskInfoVo> getTasksByPage(Integer pageNum, Integer pageSize, String state, String taskName);
    Integer countTasksByFilter(String state, String taskName);
}
