package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.TotalOverview;
import org.cpnvisualsystem.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/total")
public class TotalController {
    @Autowired
    private TotalService totalService;

    // 获取总览信息
    @RequestMapping("/overview")
    public TotalOverview getTotalOverview() {
        return totalService.getTotalOverview();
    }
}
