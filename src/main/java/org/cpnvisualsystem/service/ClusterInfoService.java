package org.cpnvisualsystem.service;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.vo.ClusterInfoVO;
import org.cpnvisualsystem.entity.vo.ClusterMapVO;
import org.cpnvisualsystem.entity.vo.PreviewWrapper;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainPreviewVO;

import java.util.List;

public interface ClusterInfoService {
    ClusterInfoVO getClusterById(Integer clusterId);
    List<TaskPreviewVO> getTasksByClusterId(Integer clusterId);
    PreviewWrapper<TrainPreviewVO> getTrainsByClusterId(Integer clusterId);
    List<ClusterInfo> getAllClusters();
    List<ClusterMapVO> getClusterMap();
}
