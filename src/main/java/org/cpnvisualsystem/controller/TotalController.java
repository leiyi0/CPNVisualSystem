package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.R;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TotalOverview;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/total")
public class TotalController {
    @Autowired
    private TotalService totalService;
    @Autowired
    private DynamicPowerService dynamicPowerService;
    @Autowired
    private StaticPowerService staticPowerService;
    // 获取总览信息
    @RequestMapping("/overview")
    public R<?> getTotalOverview() {
        return R.ok(totalService.getTotalOverview());
    }
    // 获取静态算力信息
    @RequestMapping("/staticPower")
    public R<?> getTotalStaticPower() {
        StaticPowerInfo staticPowerInfo = staticPowerService.getTotalStaticPower();
        return R.ok(staticPowerInfo);
    }

    // 获取动态算力信息
    @RequestMapping("/dynamicPower")
    public R<?> getTotalDynamicPower() {
        DynamicPowerInfo dynamicPowerInfo = dynamicPowerService.getTotalDynamicPower();
        return R.ok(dynamicPowerInfo);
    }

    /**
     * 获取总览动态算力趋势信息
     * @param minutes
     * @return
     */
    @GetMapping("/dynamicpower/trend/{minutes}")
    public R<?> getTotalDynamicPowerTrend(@PathVariable Integer minutes) {
        List<DynamicPowerInfo> dynamicPowerInfo = dynamicPowerService.getTotalDynamicPowerTrend(minutes);
        return R.ok(dynamicPowerInfo);
    }
}
