package org.cpnvisualsystem.mapper;

import org.cpnvisualsystem.entity.StaticPowerInfo;

import java.util.List;

public interface StaticPowerMapper {
    /**
     * 根据设备ID查询静态算力信息
     */
    StaticPowerInfo getStaticPowerInfo(Integer deviceId);
    /**
     * 根据设备ID列表查询静态算力信息（聚合查询）
     */
    StaticPowerInfo getStaticPowerInfoByIds(List<Integer> deviceIds);
}
