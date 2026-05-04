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
    // 5. 获取车厢静态算力信息
    @GetMapping("/staticpower/{id}")
    public R<?> getCarriageStaticPower(@PathVariable("id") Integer id) {
        return R.ok(staticPowerService.getStaticPowerByCarriageId(id));
    }

    // 6. 获取车厢动态算力信息
    @GetMapping("/dynamicpower/{id}")
    public R<?> getDynamicPowerByCarriageId(@PathVariable Integer id) {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getDynamicPowerByCarriageId(id);
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取车厢动态算力趋势信息
     * @param id
     * @param minutes
     * @return
     */
    @GetMapping("/dynamicpower/trend/{id}/{minutes}")
    public R<?> getDynamicPowerTrendByCarriageId(@PathVariable Integer id, @PathVariable Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getDynamicPowerTrendByCarriageId(id, minutes);
        return R.ok(dynamicPowerInfo);
    }

    // 车辆信息页面：车辆算力视图
    @GetMapping("/view")
    public R<CarriageViewVO> getCarriageView(@RequestParam("carriage_id") Integer carriageId) {
        return R.ok(carriageInfoService.getCarriageView(carriageId));
    }
}