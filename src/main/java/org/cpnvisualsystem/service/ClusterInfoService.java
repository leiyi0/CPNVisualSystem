package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;

import java.util.List;

public interface ClusterInfoService {
    ClusterInfo getClusterById(Integer clusterId);
    List<TaskInfo> getTasksByClusterId(Integer clusterId);
    List<TrainInfo> getTrainsByClusterId(Integer clusterId);
    List<ClusterInfo> getAllClusters();
}