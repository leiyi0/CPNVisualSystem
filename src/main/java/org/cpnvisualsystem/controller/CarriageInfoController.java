package org.cpnvisualsystem.controller;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CarriageInfoController {
    @Autowired
    private CarriageInfoService carriageInfoService;

    @GetMapping("/carriage/{id}")
    public CarriageInfo getCarriageById(@PathVariable Integer id) {
        return carriageInfoService.getById(id);
    }
}