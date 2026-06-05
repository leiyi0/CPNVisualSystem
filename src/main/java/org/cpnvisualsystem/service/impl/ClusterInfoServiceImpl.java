package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.ClusterInfoVO;
import org.cpnvisualsystem.entity.vo.ClusterMapVO;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainPreviewVO;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.ClusterInfoService;
import org.cpnvisualsystem.util.TransformUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClusterInfoServiceImpl implements ClusterInfoService {

    @Autowired
    private ClusterInfoMapper clusterInfoMapper;

    @Autowired
    private TaskInfoMapper taskInfoMapper;

    @Autowired
    private TrainInfoMapper trainInfoMapper;

    @Override
    public ClusterInfoVO getClusterById(Integer clusterId) {
        ClusterInfo cluster = clusterInfoMapper.selectById(clusterId);
        if (cluster == null) return null;
        cluster.setTrainCount(trainInfoMapper.countTrainsByClusterId(clusterId));

        ClusterInfoVO vo = new ClusterInfoVO();
        vo.setClusterId(cluster.getId());
        vo.setClusterCode(cluster.getClusterCode());
        vo.setCoverageArea(cluster.getCoverageArea());
        vo.setLongitude(cluster.getLongitude());
        vo.setLatitude(cluster.getLatitude());
        vo.setTrainCount(cluster.getTrainCount());
        vo.setStatus(cluster.getStatus());
        return vo;
    }

    @Override
    public List<TaskPreviewVO> getTasksByClusterId(Integer clusterId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByClusterId(clusterId);
        return tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
    }

    @Override
    public List<TrainPreviewVO> getTrainsByClusterId(Integer clusterId) {
        List<TrainInfo> trains = trainInfoMapper.selectTrainsByClusterId(clusterId);
        return trains.stream().map(TransformUtil::toTrainPreview).collect(Collectors.toList());
    }

    @Override
    public List<ClusterInfo> getAllClusters() {
        List<ClusterInfo> clusters = clusterInfoMapper.selectAllClusters();
        for (ClusterInfo cluster : clusters) {
            cluster.setTrainCount(trainInfoMapper.countTrainsByClusterId(cluster.getId()));
        }
        return clusters;
    }

    @Override
    public List<ClusterMapVO> getClusterMap() {
        List<ClusterInfo> clusters = clusterInfoMapper.selectAllClusters();
        return clusters.stream().map(c -> {
            ClusterMapVO vo = new ClusterMapVO();
            vo.setClusterId(c.getId());
            vo.setClusterCode(c.getClusterCode());
            vo.setLeft(c.getMapLeft());
            vo.setTop(c.getMapTop());
            vo.setStatus(c.getStatus() != null ? c.getStatus() : "正常");
            return vo;
        }).collect(Collectors.toList());
    }
}
