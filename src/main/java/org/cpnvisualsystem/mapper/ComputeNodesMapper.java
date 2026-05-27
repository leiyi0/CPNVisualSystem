package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.ComputeNodes;

import java.util.List;

@Mapper
public interface ComputeNodesMapper {
    /**
     * 根据设备ID查询设备详细信息
     */
    ComputeNodes selectById(@Param("id") Integer id);

    /**
     * 根据车厢ID查询搭载的设备列表
     */
    List<ComputeNodes> selectDevicesByCarriageId(@Param("carriageId") Integer carriageId);

    /**
     * 根据车厢ID统计设备数量
     */
    Integer countDevicesByCarriageId(@Param("carriageId") Integer carriageId);

    /**
     * 统计所有设备总数
     */
    Integer countAllDevices();

    /**
     * 统计在线设备数 (status = 'READY')
     */
    Integer countOnlineDevices();
}