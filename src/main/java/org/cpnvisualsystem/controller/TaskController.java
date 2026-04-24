package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.PageResult;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;
import org.cpnvisualsystem.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskInfoService taskInfoService;

    @GetMapping("/list")
    public R<?> listTasks(@RequestParam(value = "pageNum", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", required = false) Integer pageSize,
                          @RequestParam(value = "state", required = false) String state,
                          @RequestParam(value = "taskName", required = false) String taskName) {
        List<TaskInfoVo> data = taskInfoService.getTasksByPage(pageNum, pageSize, state, taskName);
        Integer total = taskInfoService.countTasksByFilter(state, taskName);
        PageResult<TaskInfoVo> pageResult = new PageResult<>(data, total, (pageNum == null ? 1 : pageNum), (pageSize == null ? 10 : pageSize));
        return R.ok(pageResult);
    }
}
