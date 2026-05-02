package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.ClusterInfoService;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cluster")
public class ClusterController {

    @Autowired
    private ClusterInfoService clusterInfoService;
    @Autowired
    private StaticPowerService staticPowerService;
    @Autowired
    private DynamicPowerService dynamicPowerService;
    // 1. 获取集群基本信息
    @GetMapping("/info")
    public R<?> getClusterInfo(@RequestParam("cluster_id") Integer clusterId) {
        return R.ok(clusterInfoService.getClusterById(clusterId));
    }

    // 2. 获取集群预览列表
    @GetMapping("/list")
    public R<?> listClusters() {
        return R.ok(clusterInfoService.getAllClusters());
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

    // 5. 获取集群静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getClusterStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByClusterId(id));
    }

    // 6. 获取集群动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<DynamicPowerInfo> getDynamicPowerByClusterId(@PathVariable Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByClusterId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取集群动态算力趋势信息
     * @param id
     * @param minutes
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByClusterId(@PathVariable Integer id, @PathVariable Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByClusterId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }
}