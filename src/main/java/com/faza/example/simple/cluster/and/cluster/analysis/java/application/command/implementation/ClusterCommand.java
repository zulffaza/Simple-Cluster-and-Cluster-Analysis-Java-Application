package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.Command;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.ClusterRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response.ClusterResponse;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Cluster;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.IrisDistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class ClusterCommand implements Command<ClusterRequest, ClusterResponse> {

    private static final Integer CLUSTER_ID_INITIAL_VALUE = 1;

    @Override
    public ClusterResponse execute(ClusterRequest clusterRequest) {
        calculateIrisesDistances(clusterRequest.getIrises());

        return ClusterResponse.builder()
                .clusters(
                        createCluster(clusterRequest.getIrises()))
                .build();
    }

    private void calculateIrisesDistances(List<Iris> irises) {
        List<Iris> irisesCopy = new ArrayList<>(irises);
        irises.forEach(iris ->
                calculateIrisDistances(iris, irisesCopy));
    }

    private void calculateIrisDistances(Iris iris, List<Iris> irises) {
        irises.forEach(irisCopy ->
                calculateIrisDistance(iris, irisCopy));
    }

    private void calculateIrisDistance(Iris iris, Iris irisCopy) {
        if (!isIrisIdEquals(iris, irisCopy)) {
            IrisDistance irisDistance = IrisDistance.builder()
                    .id(irisCopy.getId())
                    .distance(
                            calculateDistance(iris, irisCopy))
                    .build();

            iris.addIrisDistance(irisDistance);
        }
    }

    private Boolean isIrisIdEquals(Iris iris, Iris irisCopy) {
        return iris.getId().equals(irisCopy.getId());
    }

    private Double calculateDistance(Iris iris, Iris irisCopy) {
        Iris irisTemp = Iris.builder(
                0,
                iris.getSepalLength() - irisCopy.getSepalLength(),
                iris.getSepalWidth() - irisCopy.getSepalWidth(),
                iris.getPetalLength() - irisCopy.getPetalLength(),
                iris.getPetalWidth() - iris.getPetalWidth()).build();

        Double distance = Math.pow(irisTemp.getSepalLength(), 2) +
                Math.pow(irisTemp.getSepalWidth(), 2) +
                Math.pow(irisTemp.getPetalLength(), 2) +
                Math.pow(irisTemp.getPetalWidth(), 2);

        return Math.sqrt(distance);
    }

    private List<Cluster> createCluster(List<Iris> irises) {
        AtomicInteger id = new AtomicInteger(CLUSTER_ID_INITIAL_VALUE);

        return irises.stream()
                .map(iris ->
                        Cluster.builder(id.getAndIncrement(), Collections.singletonList(iris)).build())
                .collect(Collectors.toList());
    }
}
