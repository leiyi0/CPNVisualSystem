package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.service.CarriageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarriageInfoServiceImpl implements CarriageInfoService {
    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Override
    public CarriageInfo getById(Integer id) {
        CarriageInfo carriageInfo = carriageInfoMapper.selectById(id);
        System.out.println(carriageInfo);
        return carriageInfo;
    }
}