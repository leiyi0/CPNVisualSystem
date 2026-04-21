package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.TaskInfo;

import java.util.List;

@Mapper
public interface TaskInfoMapper {
    /**
     * 根据集群ID查询关联任务（跨表：Task -> Carriage -> Train -> Cluster）
     */
    List<TaskInfo> selectTasksByClusterId(@Param("clusterId") Integer clusterId);

    /**
     * 根据列车ID查询关联任务（跨表：Task -> Carriage -> Train）
     */
    List<TaskInfo> selectTasksByTrainId(@Param("trainId") Integer trainId);

    /**
     * 根据车厢ID查询关联任务（Task -> Carriage）
     */
    List<TaskInfo> selectTasksByCarriageId(@Param("carriageId") Integer carriageId);

    /**
     * 根据设备ID查询发起的关联任务
     */
    List<TaskInfo> selectTasksByDeviceId(@Param("deviceId") Integer deviceId);
}