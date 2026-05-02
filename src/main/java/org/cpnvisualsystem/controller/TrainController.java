package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.service.TrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/info")
    public R<?> getTrainInfo(@RequestParam("train_id") Integer trainId) {
        return R.ok(trainInfoService.getTrainById(trainId));
    }

    // 3. 获取列车任务预览列表
    @GetMapping("/tasks")
    public R<?> getTrainTasks(@RequestParam("train_id") Integer trainId) {
        return R.ok(trainInfoService.getTasksByTrainId(trainId));
    }

    // 4. 获取车厢预览列表
    @GetMapping("/carriages")
    public R<?> getTrainCarriages(@RequestParam("train_id") Integer trainId) {
        return R.ok(trainInfoService.getCarriagesByTrainId(trainId));
    }

    // 5. 获取列车静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getTrainStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByTrainId(id));
    }

    // 6. 获取列车动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<DynamicPowerInfo> getDynamicPowerByTrainId(@PathVariable Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByTrainId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取列车动态算力趋势信息
     * @param id
     * @param minutes
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByTrainId(@PathVariable Integer id, @PathVariable Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByTrainId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }

}