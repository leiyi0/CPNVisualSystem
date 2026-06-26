package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.ResourceSummary;

import java.util.List;

/**
 * resource_summary 表 Mapper，支持按层级类型+层级ID查询，以及查询整体汇总
 */
@Mapper
public interface ResourceSummaryMapper {

    /**
     * 按层级类型和层级ID查询（如 cluster/1, train/2, carriage/3, device/5）
     */
    ResourceSummary selectByLayer(@Param("layerType") String layerType,
                                  @Param("layerId") Integer layerId);

    /**
     * 查询整体汇总（layer_type='overall'）
     */
    ResourceSummary selectOverall();

    /**
     * 查询某列车下所有车厢的资源汇总（layer_type='carriage', parent_type='train'）
     */
    List<ResourceSummary> selectCarriagesByTrainId(@Param("trainId") Integer trainId);
}
