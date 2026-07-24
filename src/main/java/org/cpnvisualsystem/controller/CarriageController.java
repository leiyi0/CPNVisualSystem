package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;
import java.util.List;

@RestController
@RequestMapping("/api/carriage")
public class CarriageController {

    @Autowired
    private CarriageInfoService carriageInfoService;

    @Autowired
    private StaticPowerService staticPowerService;

    @Autowired
    private DynamicPowerService dynamicPowerService;

    // 1. 获取车厢基本信息
    // 访问路径示例: /api/carriage/info/1
    @GetMapping("/info/{id}")
    public R<?> getCarriageInfo(@PathVariable("id") Integer id) {
        return R.ok(carriageInfoService.getById(id));
    }

    // 3. 获取车厢任务预览列表
    // 访问路径示例: /api/carriage/tasks/1
    @GetMapping("/tasks/{id}")
    public R<?> getCarriageTasks(@PathVariable("id") Integer id) {
        return R.ok(carriageInfoService.getTasksByCarriageId(id));
    }

    // 4. 获取设备预览列表
    // 访问路径示例: /api/carriage/devices/1
    @GetMapping("/devices/{id}")
    public R<?> getCarriageDevices(@PathVariable("id") Integer id) {
        return R.ok(carriageInfoService.getDevicesByCarriageId(id));
    }

    // 5. 获取车厢静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getCarriageStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByCarriageId(id));
    }

    // 6. 获取车厢动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<?> getDynamicPowerByCarriageId(@PathVariable("id") Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByCarriageId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取车厢动态算力趋势信息
     * @param id 车厢ID
     * @param minutes 分钟数
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByCarriageId(
            @PathVariable("id") Integer id,
            @PathVariable("minutes") Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByCarriageId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }

    // 车厢信息页面：车厢设备算力视图
    // 访问路径示例: /api/carriage/view/1
    @GetMapping("/view/{id}")
    public R<CarriageViewVO> getCarriageView(@PathVariable("id") Integer id) {
        return R.ok(carriageInfoService.getCarriageView(id));
    }

    /**
     * 获取车厢故障状态信息
     * 访问路径示例: /api/carriage/fault/1
     */
    @GetMapping("/fault/{id}")
    public R<?> getCarriageFault(@PathVariable("id") Integer id) {
        return R.ok(carriageInfoService.getCarriageFault(id));
    }
}