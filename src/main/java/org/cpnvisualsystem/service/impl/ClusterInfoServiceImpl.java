package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.ClusterInfo;
import org.cpnvisualsystem.entity.ResourceSummary;
import org.cpnvisualsystem.entity.StaticPowerInfo;
import org.cpnvisualsystem.entity.TaskInfo;
import org.cpnvisualsystem.entity.TrainInfo;
import org.cpnvisualsystem.entity.vo.ClusterInfoVO;
import org.cpnvisualsystem.entity.vo.ClusterMapVO;
import org.cpnvisualsystem.entity.vo.PreviewWrapper;
import org.cpnvisualsystem.entity.vo.TaskPreviewVO;
import org.cpnvisualsystem.entity.vo.TrainPreviewVO;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
import org.cpnvisualsystem.mapper.ResourceSummaryMapper;
import org.cpnvisualsystem.mapper.TaskInfoMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.ClusterInfoService;
import org.cpnvisualsystem.service.StaticPowerService;
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

    @Autowired
    private StaticPowerService staticPowerService;

    @Autowired
    private ResourceSummaryMapper resourceSummaryMapper;

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

        // 填充静态算力总量
        StaticPowerInfo staticPower = staticPowerService.getStaticPowerByClusterId(clusterId);
        if (staticPower != null) {
            vo.setTotalComputePower(staticPower.getComputerPower());
            vo.setTotalComputePowerMips(staticPower.getComputerPowerMips());
            vo.setTotalStoragePower(staticPower.getStoragePower());
            vo.setTotalTransportPower(staticPower.getTransportPower());
        }

        // 从 resource_summary 补充 MIPS 计算力总量
        ResourceSummary summary = resourceSummaryMapper.selectByLayer("cluster", clusterId);
        if (summary != null && summary.getComputeMipsTotal() != null) {
            vo.setTotalComputePowerMips(summary.getComputeMipsTotal());
        }

        // 填充任务汇总
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByClusterId(clusterId);
        if (tasks != null && !tasks.isEmpty()) {
            vo.setTaskCount(tasks.size());
            double computeSum = 0, computeMipsSum = 0, storageSum = 0, transportSum = 0;
            for (TaskInfo t : tasks) {
                if (t.getComputeDemand() != null) {
                    boolean isMips = t.getComputeType() != null && t.getComputeType().toLowerCase().contains("mips");
                    if (isMips) {
                        computeMipsSum += t.getComputeDemand();
                    } else {
                        computeSum += t.getComputeDemand();
                    }
                }
                if (t.getStorageDemandMb() != null) storageSum += t.getStorageDemandMb();
                if (t.getTransportDemandMbps() != null) transportSum += t.getTransportDemandMbps();
            }
            vo.setTaskComputeUsage(Math.round(computeSum / 1_000_000_000_000.0 * 100.0) / 100.0);
            vo.setTaskComputeUsageMips(Math.round(computeMipsSum * 100.0) / 100.0);
            vo.setTaskStorageUsage(Math.round(storageSum / 1024.0 * 100.0) / 100.0);
            vo.setTaskTransportUsage(Math.round(transportSum * 100.0) / 100.0);
        }

        return vo;
    }

    @Override
    public List<TaskPreviewVO> getTasksByClusterId(Integer clusterId) {
        List<TaskInfo> tasks = taskInfoMapper.selectTasksByClusterId(clusterId);
        List<TaskPreviewVO> result = tasks.stream().map(TransformUtil::toTaskPreview).collect(Collectors.toList());
        // 从 resource_summary 表获取该集群的总算力，按任务计算类型（MIPS/FLOPS）选取对应总量计算资源占比
        ResourceSummary summary = resourceSummaryMapper.selectByLayer("cluster", clusterId);
        if (summary != null) {
            for (int i = 0; i < tasks.size(); i++) {
                TaskInfo t = tasks.get(i);
                if (t.getComputeDemand() != null) {
                    boolean isMips = t.getComputeType() != null && t.getComputeType().toLowerCase().contains("mips");
                    Double total = isMips ? summary.getComputeMipsTotal() : summary.getComputeFlopsTotal();
                    if (total != null && total > 0) {
                        result.get(i).setComputeResourceRatio(Math.round(t.getComputeDemand() / total * 100.0 * 100.0) / 100.0);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public PreviewWrapper<TrainPreviewVO> getTrainsByClusterId(Integer clusterId) {
        List<TrainInfo> trains = trainInfoMapper.selectTrainsByClusterId(clusterId);
        List<TrainPreviewVO> items = trains.stream().map(TransformUtil::toTrainPreview).collect(Collectors.toList());
        ResourceSummary summary = resourceSummaryMapper.selectByLayer("cluster", clusterId);
        return new PreviewWrapper<>(items, summary);
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
