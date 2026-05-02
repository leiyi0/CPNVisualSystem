package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.StaticPowerInfo;

/**
 * 静态算力服务接口，提供根据不同维度查询静态算力信息的方法
 */
public interface StaticPowerService {
    /**
     * 根据设备ID查询静态算力信息
     */
    StaticPowerInfo getStaticPowerByDeviceId(Integer deviceId);
    /**
     * 根据列车ID查询静态算力信息
     */
    StaticPowerInfo getStaticPowerByTrainId(Integer trainId);
    /**
     * 根据车厢ID查询静态算力信息
     */
    StaticPowerInfo getStaticPowerByCarriageId(Integer carriageId);
    /**
     * 根据集群ID查询静态算力信息
     */
    StaticPowerInfo getStaticPowerByClusterId(Integer clusterId);
    /**
     * 查询总体静态算力信息
     */
    StaticPowerInfo getTotalStaticPower();
}
