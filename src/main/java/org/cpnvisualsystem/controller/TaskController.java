package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.PageResult;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.entity.ResourceSummary;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskDetailVO;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;
import org.cpnvisualsystem.mapper.ResourceSummaryMapper;
import org.cpnvisualsystem.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private ResourceSummaryMapper resourceSummaryMapper;

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
                          @RequestParam(value = "taskName", required = false) String taskName,
                          @RequestParam(value = "deviceId", required = false) Integer deviceId) {
        List<TaskInfoVo> data = taskInfoService.getTasksByPage(pageNum, pageSize, state, taskName, deviceId);
        Integer total = taskInfoService.countTasksByFilter(state, taskName, deviceId);
        PageResult<TaskInfoVo> pageResult = new PageResult<>(data, total, (pageNum == null ? 1 : pageNum), (pageSize == null ? 10 : pageSize));
        // 附带全局 resource_summary 汇总数据
        ResourceSummary overall = resourceSummaryMapper.selectOverall();
        pageResult.setResourceSummary(overall);
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

    /**
     * 获取任务统计信息（按状态/层级/类型/优先级/匹配策略分组统计）
     */
    @GetMapping("/stats")
    public R<?> getTaskStats() {
        return R.ok(taskInfoService.getTaskStats());
    }
}
