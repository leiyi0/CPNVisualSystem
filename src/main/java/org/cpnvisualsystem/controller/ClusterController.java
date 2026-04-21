package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.ClusterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cluster")
public class ClusterController {

    @Autowired
    private ClusterInfoService clusterInfoService;

    // 1. 获取集群基本信息
    @GetMapping("/info")
    public R<?> getClusterInfo(@RequestParam("cluster_id") Integer clusterId) {
        return R.ok(clusterInfoService.getClusterById(clusterId));
    }

    // 3. 获取集群任务预览列表
    @GetMapping("/tasks")
    public R<?> getClusterTasks(@RequestParam("cluster_id") Integer clusterId) {
        return R.ok(clusterInfoService.getTasksByClusterId(clusterId));
    }

    // 4. 获取列车预览列表
    @GetMapping("/trains")
    public R<?> getClusterTrains(@RequestParam("cluster_id") Integer clusterId) {
        return R.ok(clusterInfoService.getTrainsByClusterId(clusterId));
    }
}