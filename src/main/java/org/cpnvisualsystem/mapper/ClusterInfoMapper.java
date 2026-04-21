package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.ClusterInfo;

@Mapper
public interface ClusterInfoMapper {
    /**
     * 根据ID查询集群基本信息
     */
    ClusterInfo selectById(@Param("id") Integer id);
}