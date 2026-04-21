package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // 1. 获取设备预览信息
    @GetMapping("/list")
    public R<?> getDeviceList(@RequestParam("carriage_id") Integer carriageId) {
        return R.ok(deviceService.getDevicesByCarriageId(carriageId));
    }

    // 2. 获取设备详细信息
    @GetMapping("/info")
    public R<?> getDeviceInfo(@RequestParam("device_id") Integer deviceId) {
        return R.ok(deviceService.getDeviceById(deviceId));
    }

    // 4. 获取设备相关任务
    @GetMapping("/tasks")
    public R<?> getDeviceTasks(@RequestParam("device_id") Integer deviceId) {
        return R.ok(deviceService.getTasksByDeviceId(deviceId));
    }
}
// 确保这个大括号外面没有别的类了！！！