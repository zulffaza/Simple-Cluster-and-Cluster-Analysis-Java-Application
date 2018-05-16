package com.faza.example.simple.cluster.and.cluster.analysis.java.application.main.util;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Cluster;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

public class ClusterHelper {

    private static final String WHITESPACE_REGEX = "\\s";

    private static final Integer IRIS_ID_INITIAL_VALUE = 1;
    private static final Integer SEPAL_LENGTH_INDEX = 0;
    private static final Integer SEPAL_WIDTH_INDEX = 1;
    private static final Integer PETAL_LENGTH_INDEX = 2;
    private static final Integer PETAL_WIDTH_INDEX = 3;
    private static final Integer LABEL_INDEX = 4;

    private static ClusterHelper instance;

    private ClusterHelper() {

    }

    public static ClusterHelper getInstance() {
        if (instance == null)
            instance = new ClusterHelper();

        return instance;
    }

    public List<Cluster> readClusterDataSet(List<String> lines) {
        AtomicInteger id = new AtomicInteger(IRIS_ID_INITIAL_VALUE);
        List<Cluster> clusters = new ArrayList<>();

        lines.forEach(line ->
                fillClustersFromLine(id, clusters, line));
        sortClusters(clusters);

        return clusters;
    }

    private void fillClustersFromLine(AtomicInteger id, List<Cluster> clusters, String line) {
        List<Double> doubles = parseWordsToDoubles(
                line.split(WHITESPACE_REGEX));

        Integer label = doubles.get(LABEL_INDEX).intValue();
        Cluster cluster = findClusterFromLabel(label, clusters);

        if (cluster != null)
            cluster.addIris(buildIris(id, doubles));
        else {
            Cluster newCluster = buildCluster(label,
                    buildIris(id, doubles));

            clusters.add(newCluster);
        }
    }

    private List<Double> parseWordsToDoubles(String[] words) {
        return Arrays.stream(words)
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private Cluster findClusterFromLabel(Integer label, List<Cluster> clusters) {
        return clusters.stream()
                .filter(cluster ->
                        isIdEquals(cluster.getId(), label))
                .findFirst()
                .orElse(null);
    }

    private Boolean isIdEquals(Integer id, Integer searchId) {
        return id.equals(searchId);
    }

    private Iris buildIris(AtomicInteger id, List<Double> doubles) {
        return Iris.builder(
                id.getAndIncrement(),
                doubles.get(SEPAL_LENGTH_INDEX),
                doubles.get(SEPAL_WIDTH_INDEX),
                doubles.get(PETAL_LENGTH_INDEX),
                doubles.get(PETAL_WIDTH_INDEX)).build();
    }

    private Cluster buildCluster(Integer id, Iris iris) {
        List<Iris> irises = new ArrayList<>();
        irises.add(iris);

        return Cluster.builder(id, irises)
                .build();
    }

    private void sortClusters(List<Cluster> clusters) {
        clusters.sort(
                Comparator.comparingInt(Cluster::getId));
    }

    public void printClusters(List<Cluster> clusters) {
        IrisHelper irisHelper = IrisHelper.getInstance();

        clusters.forEach(cluster -> {
            System.out.println(cluster);
            irisHelper.printIrises(cluster.getIrises());
        });
    }
}
