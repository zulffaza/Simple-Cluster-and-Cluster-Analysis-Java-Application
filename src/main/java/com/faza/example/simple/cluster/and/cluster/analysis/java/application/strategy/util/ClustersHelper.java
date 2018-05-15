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

    public void buildNewCluster(Cluster cluster, List<Cluster> clusters) throws Exception {
        Integer clusterId = cluster.getId();
        Integer nearestClusterId = searchNearestClusterId(cluster);

        checkAndMergeNearestCluster(clusterId, nearestClusterId, clusters);
    }

    private Integer searchNearestClusterId(Cluster cluster) throws Exception {
        return cluster.getClusterDistances().stream()
                .min(Comparator.comparingDouble(ClusterDistance::getDistance))
                .orElseThrow(Exception::new)
                .getId();
    }

    private void checkAndMergeNearestCluster(Integer clusterId, Integer nearestClusterId, List<Cluster> clusters)
            throws Exception {
        Cluster nearestCluster = findCluster(nearestClusterId, clusters);
        Integer secondNearestClusterId = searchNearestClusterId(nearestCluster);

        if (clusterId.equals(secondNearestClusterId))
            mergeNearestCluster(clusterId, nearestCluster, clusters);
        else
            checkAndMergeNearestCluster(nearestClusterId, secondNearestClusterId, clusters);
    }

    private void mergeNearestCluster(Integer clusterId, Cluster nearestCluster, List<Cluster> clusters)
            throws Exception {
        Cluster cluster = findCluster(clusterId, clusters);
        nearestCluster.getIrises()
                .forEach(cluster::addIris);
        clusters.remove(nearestCluster);
    }

    private Cluster findCluster(Integer clusterId, List<Cluster> clusters) throws Exception {
        return clusters.stream()
                .filter(cluster ->
                        isIdEquals(cluster.getId(), clusterId))
                .findFirst()
                .orElseThrow(Exception::new);
    }

    private Boolean isIdEquals(Integer id, Integer searchId) {
        return id.equals(searchId);
    }
}
