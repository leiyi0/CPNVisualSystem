package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.service.TrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.cpnvisualsystem.entity.vo.CarriageResourceVO;
import org.cpnvisualsystem.entity.vo.TrainViewVO;

import java.util.List;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    @Autowired
    private TrainInfoService trainInfoService;
    @Autowired
    private StaticPowerService staticPowerService;
    @Autowired
    private DynamicPowerService dynamicPowerService;

    // 1. 获取列车基本信息
    // 访问路径示例: /api/train/info/1
    @GetMapping("/info/{id}")
    public R<?> getTrainInfo(@PathVariable("id") Integer id) {
        return R.ok(trainInfoService.getTrainById(id));
    }

    // 3. 获取列车任务预览列表
    // 访问路径示例: /api/train/tasks/1
    @GetMapping("/tasks/{id}")
    public R<?> getTrainTasks(@PathVariable("id") Integer id) {
        return R.ok(trainInfoService.getTasksByTrainId(id));
    }

    // 4. 获取车厢预览列表
    // 访问路径示例: /api/train/carriages/1
    @GetMapping("/carriages/{id}")
    public R<?> getTrainCarriages(@PathVariable("id") Integer id) {
        return R.ok(trainInfoService.getCarriagesByTrainId(id));
    }

    // 5. 获取列车静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getTrainStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByTrainId(id));
    }

    // 6. 获取列车动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<DynamicPowerInfo> getDynamicPowerByTrainId(@PathVariable("id") Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByTrainId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取列车动态算力趋势信息
     * @param id 列车ID
     * @param minutes 分钟数
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByTrainId(
            @PathVariable("id") Integer id,
            @PathVariable("minutes") Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByTrainId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }

    // 列车各车厢资源统计（计算/运载/存储力使用率与使用量、设备数量、任务数量）
    @GetMapping("/{id}/carriage-resources")
    public R<List<CarriageResourceVO>> getCarriageResources(@PathVariable("id") Integer id) {
        return R.ok(trainInfoService.getCarriageResourcesByTrainId(id));
    }

    // 列车信息页面：车厢设备分布视图
    // 访问路径示例: /api/train/view/1
    @GetMapping("/view/{id}")
    public R<TrainViewVO> getTrainView(@PathVariable("id") Integer id) {
        return R.ok(trainInfoService.getTrainView(id));
    }

}