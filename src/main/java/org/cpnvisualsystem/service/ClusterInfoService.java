package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainPreviewVO;

import java.util.List;

public interface ClusterInfoService {
    ClusterInfo getClusterById(Integer clusterId);
    List<TaskPreviewVO> getTasksByClusterId(Integer clusterId);
    List<TrainPreviewVO> getTrainsByClusterId(Integer clusterId);
    List<ClusterInfo> getAllClusters();
    List<org.cpnvisualsystem.entity.vo.ClusterMapVO> getClusterMap();
}
