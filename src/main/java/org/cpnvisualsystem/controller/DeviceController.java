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

    // 1. 获取设备预览信息 (根据车厢ID获取设备列表)
    // 访问路径示例: /api/device/list/1
    @GetMapping("/list/{id}")
    public R<?> getDeviceList(@PathVariable("id") Integer id) {
        return R.ok(deviceService.getDevicesByCarriageId(id));
    }

    // 2. 获取设备详细信息 (根据设备ID获取详情)
    // 访问路径示例: /api/device/info/10
    @GetMapping("/info/{id}")
    public R<?> getDeviceInfo(@PathVariable("id") Integer id) {
        return R.ok(deviceService.getDeviceById(id));
    }

    // 4. 获取设备相关任务
    // 访问路径示例: /api/device/tasks/10
    @GetMapping("/tasks/{id}")
    public R<?> getDeviceTasks(@PathVariable("id") Integer id) {
        return R.ok(deviceService.getTasksByDeviceId(id));
    }

    // 5. 获取设备静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getDeviceStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByDeviceId(id));
    }

    // 6. 获取设备动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<?> getDynamicPower(@PathVariable("id") Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByDeviceId(id);
        return R.ok(dynamicPowerInfo);
    }

    // 7. 获取设备动态算力趋势信息
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByDeviceId(
            @PathVariable("id") Integer id,
            @PathVariable("minutes") Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByDeviceId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }
}