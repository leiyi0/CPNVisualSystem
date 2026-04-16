package org.cpnvisualsystem.service;


import org.cpnvisualsystem.entity.CarriageInfo;
import org.springframework.stereotype.Service;

public interface CarriageInfoService {
    CarriageInfo getById(Integer id);
}