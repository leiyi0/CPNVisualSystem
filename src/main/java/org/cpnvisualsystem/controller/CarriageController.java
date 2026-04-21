package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carriage")
public class CarriageController {

    @Autowired
    private CarriageInfoService carriageInfoService;

    // 1. 获取车厢基本信息
    @GetMapping("/info")
    public R<?> getCarriageInfo(@RequestParam("carriage_id") Integer carriageId) {
        return R.ok(carriageInfoService.getById(carriageId));
    }

    // 3. 获取车厢任务预览列表
    @GetMapping("/tasks")
    public R<?> getCarriageTasks(@RequestParam("carriage_id") Integer carriageId) {
        return R.ok(carriageInfoService.getTasksByCarriageId(carriageId));
    }

    // 4. 获取设备预览列表
    @GetMapping("/devices")
    public R<?> getCarriageDevices(@RequestParam("carriage_id") Integer carriageId) {
        return R.ok(carriageInfoService.getDevicesByCarriageId(carriageId));
    }
}