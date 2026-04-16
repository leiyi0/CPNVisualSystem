package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.CarriageInfo;
public interface CarriageInfoMapper {
    CarriageInfo selectById(@Param("id") Integer id);
}