package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.Command;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.ClusterCommandRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response.ClusterCommandResponse;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.model.request.ClusterStrategyRequest;

import java.util.Collections;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class ClusterCommand implements Command<ClusterCommandRequest, ClusterCommandResponse> {

    @Override
    public ClusterCommandResponse execute(ClusterCommandRequest clusterCommandRequest) throws Exception {
        clusterCommandRequest.getClusterStrategy()
                .execute(ClusterStrategyRequest.builder()
                        .irises(
                                clusterCommandRequest.getIrises())
                        .build());

        clusterCommandRequest.getIrises()
                .forEach(iris -> {
                    System.out.println(iris);
                    iris.getIrisDistances().forEach(irisDistance ->
                            System.out.println("\t" + irisDistance));
                });

        return ClusterCommandResponse.builder()
                .clusters(
                        Collections.emptyList())
                .build();
    }
}
