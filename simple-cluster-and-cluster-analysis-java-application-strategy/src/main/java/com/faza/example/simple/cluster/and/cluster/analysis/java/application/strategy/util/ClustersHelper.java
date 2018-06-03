package com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.util;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Cluster;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.ClusterDistance;

import java.util.Comparator;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

public class ClustersHelper {

    private static ClustersHelper instance;

    private ClustersHelper() {

    }

    public static ClustersHelper getInstance() {
        if (instance == null)
            instance = new ClustersHelper();

        return instance;
    }

    public void buildNewCluster(List<Cluster> clusters) throws Exception {
        Cluster cluster = clusters.stream()
                .min(Comparator.comparingDouble(this::getMinDistance))
                .orElseThrow(Exception::new);

        ClusterDistance clusterDistance = getMinClusterDistance(cluster);

        mergeNearestCluster(cluster, clusterDistance.getId(), clusters);
    }

    private Double getMinDistance(Cluster cluster) {
        Double distance;

        try {
            distance = getMinClusterDistance(cluster)
                    .getDistance();
        } catch (Exception e) {
            distance = null;
        }

        return distance;
    }

    private ClusterDistance getMinClusterDistance(Cluster cluster) {
        return cluster.getClusterDistances()
                .stream()
                .min(Comparator.comparingDouble(
                        ClusterDistance::getDistance))
                .orElse(null);
    }

    private void mergeNearestCluster(Cluster cluster, Integer nearestClusterId, List<Cluster> clusters)
            throws Exception {
        Cluster nearestCluster = findCluster(nearestClusterId, clusters);
        nearestCluster.getIrises()
                .forEach(cluster::addIris);
        clusters.remove(nearestCluster);
    }

    private Cluster findCluster(Integer clusterId, List<Cluster> clusters) throws Exception {
        return clusters.stream()
                .filter(cluster ->
                        cluster.isIdEquals(clusterId))
                .findFirst()
                .orElseThrow(Exception::new);
    }
}
