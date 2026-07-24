package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarriageInfoMapper {
    /**
     * 根据ID查询车厢基本信息
     */
    CarriageInfo selectById(@Param("id") Integer id);

    /**
     * 根据列车ID查询下属所有车厢列表
     */
    List<CarriageInfo> selectCarriagesByTrainId(@Param("trainId") Integer trainId);

    List<CarriageViewVO> selectCarriageViewByTrainId(@Param("trainId") Integer trainId);
    CarriageViewVO selectCarriageViewByCarriageId(@Param("carriageId") Integer carriageId);

    /**
     * 根据列车ID统计车厢数量
     */
    Integer countCarriagesByTrainId(@Param("trainId") Integer trainId);

    /**
     * 按集群ID统计车厢各状态数量（跨表：carriage → train → cluster）
     */
    List<Map<String, Object>> countCarriagesByClusterIdGroupByStatus(@Param("clusterId") Integer clusterId);

    /**
     * 按列车ID统计车厢各状态数量
     */
    List<Map<String, Object>> countCarriagesByTrainIdGroupByStatus(@Param("trainId") Integer trainId);
}