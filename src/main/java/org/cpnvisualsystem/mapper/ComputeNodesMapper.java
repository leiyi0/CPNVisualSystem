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
     * 根据车厢ID查询搭载的设备列表 (桥接 compute_power_tag 表查询)
     */
    List<ComputeNodes> selectDevicesByCarriageId(@Param("carriageId") Integer carriageId);
}