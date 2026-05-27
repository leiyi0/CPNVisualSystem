package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.TotalOverview;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
import org.cpnvisualsystem.mapper.ComputeNodesMapper;
import org.cpnvisualsystem.mapper.TrainInfoMapper;
import org.cpnvisualsystem.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    private ClusterInfoMapper clusterInfoMapper;
    @Autowired
    private TrainInfoMapper trainInfoMapper;
    @Autowired
    private ComputeNodesMapper computeNodesMapper;

    @Override
    public TotalOverview getTotalOverview() {
        TotalOverview overview = new TotalOverview();
        overview.setClusterCount(clusterInfoMapper.countClusters());
        overview.setTrainCount(trainInfoMapper.countTrains());

        int total = computeNodesMapper.countAllDevices();
        if (total > 0) {
            int online = computeNodesMapper.countOnlineDevices();
            overview.setOnlineRate(Math.round(online * 10000.0 / total) / 100.0);
        } else {
            overview.setOnlineRate(0.0);
        }
        return overview;
    }
}
