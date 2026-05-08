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
    // 修改后访问路径示例: /api/cluster/info/1
    @GetMapping("/info/{id}")
    public R<?> getClusterInfo(@PathVariable("id") Integer id) {
        return R.ok(clusterInfoService.getClusterById(id));
    }

    // 2. 获取集群预览列表
    @GetMapping("/list")
    public R<?> listClusters() {
        return R.ok(clusterInfoService.getAllClusters());
    }

    // 3. 获取集群任务预览列表
    // 修改后访问路径示例: /api/cluster/tasks/1
    @GetMapping("/tasks/{id}")
    public R<?> getClusterTasks(@PathVariable("id") Integer id) {
        return R.ok(clusterInfoService.getTasksByClusterId(id));
    }

    // 4. 获取列车预览列表
    // 修改后访问路径示例: /api/cluster/trains/1
    @GetMapping("/trains/{id}")
    public R<?> getClusterTrains(@PathVariable("id") Integer id) {
        return R.ok(clusterInfoService.getTrainsByClusterId(id));
    }

    // 5. 获取集群静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getClusterStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByClusterId(id));
    }

    // 6. 获取集群动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<DynamicPowerInfo> getDynamicPowerByClusterId(@PathVariable("id") Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByClusterId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取集群动态算力趋势信息
     * @param id 集群ID
     * @param minutes 分钟数
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByClusterId(
            @PathVariable("id") Integer id,
            @PathVariable("minutes") Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByClusterId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }
}