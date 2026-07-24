package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ResourceSummary;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.mapper.ResourceSummaryMapper;
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

    @Autowired
    private ResourceSummaryMapper resourceSummaryMapper;
    @Override
    public StaticPowerInfo getStaticPowerByDeviceId(Integer deviceId) {
        return convertUnits(staticPowerMapper.getStaticPowerInfo(deviceId));
    }

    @Override
    public StaticPowerInfo getStaticPowerByCarriageId(Integer carriageId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByCarriageId(carriageId);
        return convertUnits(staticPowerMapper.getStaticPowerInfoByIds(deviceIds));
    }

    @Override
    public StaticPowerInfo getStaticPowerByClusterId(Integer clusterId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByClusterId(clusterId);
        return convertUnits(staticPowerMapper.getStaticPowerInfoByIds(deviceIds));
    }

    @Override
    public StaticPowerInfo getStaticPowerByTrainId(Integer trainId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByTrainId(trainId);
        return convertUnits(staticPowerMapper.getStaticPowerInfoByIds(deviceIds));
    }

    @Override
    public StaticPowerInfo getTotalStaticPower() {
        ResourceSummary overall = resourceSummaryMapper.selectOverall();
        if (overall == null) {
            return new StaticPowerInfo();
        }
        StaticPowerInfo info = new StaticPowerInfo();
        info.setComputerPower(overall.getComputeFlopsTotal());
        info.setComputerPowerMips(overall.getComputeMipsTotal());
        // resource_summary.storage_total 单位为 MB，需转换为 GB
        if (overall.getStorageTotal() != null) {
            info.setStoragePower(Math.round(overall.getStorageTotal() / 1024.0 * 100.0) / 100.0);
        }
        info.setTransportPower(overall.getTransportTotal());
        return info;
    }

    private StaticPowerInfo convertUnits(StaticPowerInfo info) {
        if (info == null) return null;
        if (info.getComputerPower() != null) {
            info.setComputerPower(Math.round(info.getComputerPower() / 1_000_000_000_000.0 * 100.0) / 100.0);
        }
        if (info.getStoragePower() != null) {
            info.setStoragePower(Math.round(info.getStoragePower() / 1024.0 * 100.0) / 100.0);
        }
        if (info.getTransportPower() != null) {
            info.setTransportPower(Math.round(info.getTransportPower() / 1000.0 * 100.0) / 100.0);
        }
        return info;
    }


}
