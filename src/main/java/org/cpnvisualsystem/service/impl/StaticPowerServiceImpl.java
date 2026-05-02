package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.mapper.StaticPowerMapper;
import org.cpnvisualsystem.service.StaticPowerService;
import org.cpnvisualsystem.util.DeviceIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaticPowerServiceImpl implements StaticPowerService {
    @Autowired
    private StaticPowerMapper staticPowerMapper;

    @Autowired
    private DeviceIdUtil deviceIdUtil;
    @Override
    public StaticPowerInfo getStaticPowerByDeviceId(Integer deviceId) {
        return staticPowerMapper.getStaticPowerInfo(deviceId);
    }

    @Override
    public StaticPowerInfo getStaticPowerByCarriageId(Integer carriageId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByCarriageId(carriageId);
        return staticPowerMapper.getStaticPowerInfoByIds(deviceIds);
    }

    @Override
    public StaticPowerInfo getStaticPowerByClusterId(Integer clusterId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByClusterId(clusterId);
        return staticPowerMapper.getStaticPowerInfoByIds(deviceIds);
    }

    @Override
    public StaticPowerInfo getStaticPowerByTrainId(Integer trainId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByTrainId(trainId);
        return staticPowerMapper.getStaticPowerInfoByIds(deviceIds);
    }

    @Override
    public StaticPowerInfo getTotalStaticPower() {
        List<Integer> deviceIds = deviceIdUtil.getAllDeviceIds();
        if (deviceIds.isEmpty()) {
            return new StaticPowerInfo();
        }
        return staticPowerMapper.getStaticPowerInfoByIds(deviceIds);
    }


}
