package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.vo.CarriageViewVO;

import java.util.List;

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
}