package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.DeviceService;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private StaticPowerService staticPowerService;
    @Autowired
    private DynamicPowerService dynamicPowerService;

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
    // 5. 获取设备静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getDeviceStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByDeviceId(id));
    }
    // 6. 获取设备动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<?> getDynamicPower(@PathVariable Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByDeviceId(id);
        return R.ok(dynamicPowerInfo);
    }
    // 7. 获取设备动态算力趋势信息
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByDeviceId(@PathVariable Integer id, @PathVariable Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByDeviceId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }
}
