package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.ClusterInfo;
public interface ClusterInfoMapper {
    ClusterInfo selectById(@Param("id") Long id);
}