package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.TrainInfo;

import java.util.List;

@Mapper
public interface TrainInfoMapper {
    /**
     * 根据ID查询列车基本信息
     */
    TrainInfo selectById(@Param("id") Integer id);

    /**
     * 根据集群ID查询下属所有列车列表
     */
    List<TrainInfo> selectTrainsByClusterId(@Param("clusterId") Integer clusterId);
}