package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.ClusterStrategy;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

@Data
@Builder
public class ClusterCommandRequest {

    private Integer numberOfCluster;

    private List<Iris> irises;

    private ClusterStrategy clusterStrategy;
}
