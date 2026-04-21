package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.TrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/train")
public class TrainController {

    @Autowired
    private TrainInfoService trainInfoService;

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
}