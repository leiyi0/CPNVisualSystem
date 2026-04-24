package org.cpnvisualsystem.service.impl;

import org.cpnvisualsystem.entity.TotalOverview;
import org.cpnvisualsystem.mapper.ClusterInfoMapper;
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

    @Override
    public TotalOverview getTotalOverview() {
        return new TotalOverview(clusterInfoMapper.countClusters(), trainInfoMapper.countTrains());
    }
}
