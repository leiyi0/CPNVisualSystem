package org.cpnvisualsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cpnvisualsystem.entity.TaskExecuteLog;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.vo.TaskInfoVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 分页并可按状态/名称筛选任务列表
     */
    List<TaskInfo> selectTasksByPage(@Param("offset") Integer offset,
                                       @Param("limit") Integer limit,
                                       @Param("state") String state,
                                       @Param("taskName") String taskName,
                                       @Param("deviceId") Integer deviceId);

    /**
     * 根据筛选条件统计任务总数
     */
    Integer countTasksByFilter(@Param("state") String state,
                               @Param("taskName") String taskName,
                               @Param("deviceId") Integer deviceId);
    /**
     * 根据任务ID查询执行日志列表
     */
    List<TaskExecuteLog> selectLogsByTaskId(Integer taskId);

    /**
     * 根据主键ID查询任务详情
     */
    TaskInfo selectTaskById(@Param("id") Long id);

    // ========== 故障状态统计（按层级+状态GROUP BY） ==========

    List<Map<String, Object>> countTasksByClusterIdGroupByState(@Param("clusterId") Integer clusterId);

    List<Map<String, Object>> countTasksByTrainIdGroupByState(@Param("trainId") Integer trainId);

    List<Map<String, Object>> countTasksByCarriageIdGroupByState(@Param("carriageId") Integer carriageId);

    // ========== 任务统计（全局GROUP BY） ==========

    List<Map<String, Object>> countTasksGroupByState();

    List<Map<String, Object>> countTasksGroupByType();

    List<Map<String, Object>> countTasksGroupByPriority();

    List<Map<String, Object>> countTasksGroupByMatchStrategy();

    List<Map<String, Object>> countTasksGroupByLevel();
}