package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.DynamicPowerMapper;
import org.cpnvisualsystem.service.DynamicPowerService;
import org.cpnvisualsystem.util.DeviceIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicPowerServiceImpl implements DynamicPowerService {
    @Autowired
    private DynamicPowerMapper dynamicPowerMapper;

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private DeviceIdUtil deviceIdUtil;

    @Override
    public DynamicPowerInfo getDynamicPowerByDeviceId(Integer deviceId) {
        return dynamicPowerMapper.getDynamicPowerInfo(deviceId);
    }

    @Override
    public DynamicPowerInfo getDynamicPowerByCarriageId(Integer carriageId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByCarriageId(carriageId);
        if (deviceIds.isEmpty()) {
            return new DynamicPowerInfo();
        }
        return dynamicPowerMapper.getDynamicPowerInfoByIds(deviceIds);
    }

    @Override
    public DynamicPowerInfo getDynamicPowerByTrainId(Integer trainId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByTrainId(trainId);
        if (deviceIds.isEmpty()) {
            return new DynamicPowerInfo();
        }
        return dynamicPowerMapper.getDynamicPowerInfoByIds(deviceIds);
    }

    @Override
    public DynamicPowerInfo getDynamicPowerByClusterId(Integer clusterId) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByClusterId(clusterId);
        if (deviceIds.isEmpty()) {
            return new DynamicPowerInfo();
        }
        return dynamicPowerMapper.getDynamicPowerInfoByIds(deviceIds);
    }

    @Override
    public DynamicPowerInfo getTotalDynamicPower() {
        List<Integer> deviceIds = deviceIdUtil.getAllDeviceIds();
        if (deviceIds.isEmpty()) {
            return new DynamicPowerInfo();
        }
        return dynamicPowerMapper.getDynamicPowerInfoByIds(deviceIds);
    }

    private int calcBucketSeconds(int minutes) {
        int bucket = (int) Math.ceil(minutes * 60.0 / 12);
        return Math.max(30, bucket);
    }

    @Override
    public List<DynamicPowerInfo> getDynamicPowerTrendByDeviceId(Integer deviceId, Integer minutes) {
        List<Integer> deviceIds = new ArrayList<>();
        deviceIds.add(deviceId);
        return dynamicPowerMapper.getComputePowerTrendByIds(deviceIds, minutes, calcBucketSeconds(minutes));
    }

    @Override
    public List<DynamicPowerInfo> getDynamicPowerTrendByCarriageId(Integer carriageId, Integer minutes) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByCarriageId(carriageId);
        if (deviceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return dynamicPowerMapper.getComputePowerTrendByIds(deviceIds, minutes, calcBucketSeconds(minutes));
    }

    @Override
    public List<DynamicPowerInfo> getDynamicPowerTrendByTrainId(Integer trainId, Integer minutes) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByTrainId(trainId);
        if (deviceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return dynamicPowerMapper.getComputePowerTrendByIds(deviceIds, minutes, calcBucketSeconds(minutes));
    }

    @Override
    public List<DynamicPowerInfo> getDynamicPowerTrendByClusterId(Integer clusterId, Integer minutes) {
        List<Integer> deviceIds = deviceIdUtil.getDeviceIdsByClusterId(clusterId);
        if (deviceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return dynamicPowerMapper.getComputePowerTrendByIds(deviceIds, minutes, calcBucketSeconds(minutes));
    }

    @Override
    public List<DynamicPowerInfo> getTotalDynamicPowerTrend(Integer minutes) {
        List<Integer> deviceIds = deviceIdUtil.getAllDeviceIds();
        if (deviceIds.isEmpty()) {
            return new ArrayList<>();
        }
        return dynamicPowerMapper.getComputePowerTrendByIds(deviceIds, minutes, calcBucketSeconds(minutes));
    }
}
