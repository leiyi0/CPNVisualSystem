package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.ClusterInfo;

import java.util.List;

@Mapper
public interface ClusterInfoMapper {
    /**
     * 查询集群数量
     */
    Integer countClusters();

    /**
     * 根据ID查询集群基本信息
     */
    ClusterInfo selectById(@Param("id") Integer id);

    /**
     * 查询所有集群预览列表
     */
    List<ClusterInfo> selectAllClusters();
}