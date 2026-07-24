package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.TrainDeviceStatsVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrainInfoMapper {
    /**
     * 查询列车数量
     */
    Integer countTrains();
    /**
     * 根据ID查询列车基本信息
     */
    TrainInfo selectById(@Param("id") Integer id);

    /**
     * 根据集群ID查询下属所有列车列表
     */
    List<TrainInfo> selectTrainsByClusterId(@Param("clusterId") Integer clusterId);

    /**
     * 根据集群ID统计列车数量
     */
    Integer countTrainsByClusterId(@Param("clusterId") Integer clusterId);

    /**
     * 查询指定列车的设备统计（设备总数 + 非正常状态空余设备数）
     */
    TrainDeviceStatsVO selectDeviceStatsByTrainId(@Param("trainId") Integer trainId);

    /**
     * 按集群ID统计列车各状态数量
     */
    List<Map<String, Object>> countTrainsByClusterIdGroupByStatus(@Param("clusterId") Integer clusterId);
}