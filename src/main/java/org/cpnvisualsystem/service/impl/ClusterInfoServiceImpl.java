package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.ClusterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterInfoServiceImpl implements ClusterInfoService {

    @Autowired
    private ClusterInfoMapper clusterInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    @Override
    public ClusterInfo getClusterById(Integer clusterId) {
        return clusterInfoMapper.selectById(clusterId);
    }

    @Override
    public List<TaskInfo> getTasksByClusterId(Integer clusterId) {
        return taskInfoMapper.selectTasksByClusterId(clusterId);
    }

    @Override
    public List<TrainInfo> getTrainsByClusterId(Integer clusterId) {
        return trainInfoMapper.selectTrainsByClusterId(clusterId);
    }

    @Override
    public List<ClusterInfo> getAllClusters() {
        return clusterInfoMapper.selectAllClusters();
    }
}