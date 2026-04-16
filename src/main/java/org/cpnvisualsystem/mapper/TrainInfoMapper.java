package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.TrainInfo;

public interface TrainInfoMapper {
    TrainInfo selectById(@Param("id") Long id);
}