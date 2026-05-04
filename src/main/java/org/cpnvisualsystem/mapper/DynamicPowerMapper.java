package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.DynamicPowerInfo;
import org.cpnvisualsystem.entity.NodeMetricsInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface DynamicPowerMapper {
    /**
     * 根据IP查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerInfoByIp(@Param("ip") String ip);

    /**
     * 根据IP列表查询动态算力信息（聚合查询）
     */
    DynamicPowerInfo getDynamicPowerInfoByIps(@Param("ips") List<String> ips);

    List<DynamicPowerInfo> getComputePowerTrend(@Param("ips") List<String> ips, @Param("minutes") Integer minutes);

    /**
     * 根据IP查询指定分钟数内的算力变化数组
     */
    List<DynamicPowerInfo> getDynamicPowerHistoryByIp(@Param("ip") String ip, @Param("minutes") Integer minutes);

    /**
     * 根据设备ID查询动态算力信息
     */
    DynamicPowerInfo getDynamicPowerInfo(@Param("deviceId") Integer deviceId);

    /**
     * 根据设备ID列表查询动态算力信息（聚合查询）
     */
    DynamicPowerInfo getDynamicPowerInfoByIds(@Param("deviceIds") List<Integer> deviceIds);

    List<DynamicPowerInfo> getComputePowerTrendByIds(@Param("deviceIds") List<Integer> deviceIds, @Param("minutes") Integer minutes);

    /**
     * 查询单个设备的最新节点指标信息
     */
    NodeMetricsInfo getLatestNodeMetricsByDeviceId(@Param("deviceId") Integer deviceId);

    /**
     * 查询多个设备的最新节点指标信息
     */
    List<NodeMetricsInfo> getLatestNodeMetricsByDeviceIds(@Param("deviceIds") List<Integer> deviceIds);
}
