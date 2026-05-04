package org.cpnvisualsystem.util;

import org.cpnvisualsystem.entity.CarriageInfo;
import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.ComputeNodes;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.mapper.CarriageInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备ID和IP获取工具类
 * 用于从不同层级（车厢、列车、集群）获取设备ID和IP列表
 */
@Component
public class DeviceIdUtil {

    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Autowired
    private CarriageInfoMapper carriageInfoMapper;

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    @Autowired
    private ClusterInfoMapper clusterInfoMapper;

    /**
     * 根据车厢ID获取设备ID列表
     * @param carriageId 车厢ID
     * @return 设备ID列表
     */
    public List<Integer> getDeviceIdsByCarriageId(Integer carriageId) {
        List<ComputeNodes> computeNodes = computeNodesMapper.selectDevicesByCarriageId(carriageId);
        return computeNodes.stream().map(ComputeNodes::getId).toList();
    }

    /**
     * 根据列车ID获取设备ID列表
     * @param trainId 列车ID
     * @return 设备ID列表
     */
    public List<Integer> getDeviceIdsByTrainId(Integer trainId) {
        List<CarriageInfo> carriageInfos = carriageInfoMapper.selectCarriagesByTrainId(trainId);
        List<Integer> deviceIds = new ArrayList<>();
        for (CarriageInfo carriageInfo : carriageInfos) {
            Integer carriageId = carriageInfo.getId();
            List<ComputeNodes> computeNodes = computeNodesMapper.selectDevicesByCarriageId(carriageId);
            deviceIds.addAll(computeNodes.stream().map(ComputeNodes::getId).toList());
        }
        return deviceIds;
    }

    /**
     * 根据集群ID获取设备ID列表
     * @param clusterId 集群ID
     * @return 设备ID列表
     */
    public List<Integer> getDeviceIdsByClusterId(Integer clusterId) {
        List<TrainInfo> trainInfos = trainInfoMapper.selectTrainsByClusterId(clusterId);
        List<Integer> deviceIds = new ArrayList<>();
        for (TrainInfo trainInfo : trainInfos) {
            Integer trainId = trainInfo.getId();
            List<CarriageInfo> carriageInfos = carriageInfoMapper.selectCarriagesByTrainId(trainId);
            List<Integer> carriageIds = carriageInfos.stream().map(CarriageInfo::getId).toList();
            for (Integer carriageId : carriageIds) {
                List<ComputeNodes> computeNodes = computeNodesMapper.selectDevicesByCarriageId(carriageId);
                deviceIds.addAll(computeNodes.stream().map(ComputeNodes::getId).toList());
            }
        }
        return deviceIds;
    }

    /**
     * 获取所有设备ID列表
     * @return 设备ID列表
     */
    public List<Integer> getAllDeviceIds() {
        List<ClusterInfo> clusterInfos = clusterInfoMapper.selectAllClusters();
        List<Integer> deviceIds = new ArrayList<>();
        for (ClusterInfo clusterInfo : clusterInfos) {
            Integer clusterId = clusterInfo.getId();
            List<TrainInfo> trainInfos = trainInfoMapper.selectTrainsByClusterId(clusterId);
            for (TrainInfo trainInfo : trainInfos) {
                Integer trainId = trainInfo.getId();
                List<CarriageInfo> carriageInfos = carriageInfoMapper.selectCarriagesByTrainId(trainId);
                for (CarriageInfo carriageInfo : carriageInfos) {
                    Integer carriageId = carriageInfo.getId();
                    List<ComputeNodes> computeNodes = computeNodesMapper.selectDevicesByCarriageId(carriageId);
                    deviceIds.addAll(computeNodes.stream().map(ComputeNodes::getId).toList());
                }
            }
        }
        return deviceIds;
    }
}
