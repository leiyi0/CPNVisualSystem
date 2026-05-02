package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.PageResult;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskDetailVO;
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

    /**
     * 分页查询任务列表，支持根据状态和任务名称进行过滤
     * @param pageNum
     * @param pageSize
     * @param state
     * @param taskName
     * @return
     */
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
    /**
     * 获取任务执行日志
     * @param taskId
     * @return
     */
    @GetMapping("/logs/{taskId}")
    public R<?> getTaskLogs(@PathVariable("taskId") Integer taskId) {
        return R.ok(taskInfoService.getLogsByTaskId(taskId));
    }

    /**
     * 获取任务详细信息（含源/目标设备最新动态信息）
     */
    @GetMapping("/detail/{id}")
    public R<TaskDetailVO> getTaskDetail(@PathVariable("id") Long id) {
        TaskDetailVO vo = taskInfoService.getTaskDetailById(id);
        return R.ok(vo);
    }
}
