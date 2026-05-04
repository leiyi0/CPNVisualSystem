package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.DynamicPowerInfo;

import java.util.List;
import java.util.Map;

public interface DynamicPowerService {
    /**
     * 根据设备ID查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerByDeviceId(Integer deviceId);

    /**
     * 根据车厢ID查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerByCarriageId(Integer carriageId);

    /**
     * 根据列车ID查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerByTrainId(Integer trainId);

    /**
     * 根据集群ID查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerByClusterId(Integer clusterId);

    /**
     * 查询总体动态算力信息
     */
    DynamicPowerInfo getTotalDynamicPower();


    /**
     * 根据设备ID查询算力趋势
     */
    List<DynamicPowerInfo> getDynamicPowerTrendByDeviceId(Integer deviceId, Integer minutes);

    /**
     * 根据车厢ID查询算力趋势
     */
    List<DynamicPowerInfo> getDynamicPowerTrendByCarriageId(Integer carriageId, Integer minutes);

    /**
     * 根据列车ID查询算力趋势
     */
    List<DynamicPowerInfo> getDynamicPowerTrendByTrainId(Integer trainId, Integer minutes);

    /**
     * 根据集群ID查询算力趋势
     */
    List<DynamicPowerInfo> getDynamicPowerTrendByClusterId(Integer clusterId, Integer minutes);

    /**
     * 查询总体算力趋势
     */
    List<DynamicPowerInfo> getTotalDynamicPowerTrend(Integer minutes);
}
