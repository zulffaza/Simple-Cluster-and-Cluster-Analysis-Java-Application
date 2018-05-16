package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.Command;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.CalculateErrorRatioCommandRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response.CalculateErrorRatioCommandResponse;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.util.PermutationHelper;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Cluster;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

public class CalculateErrorRatioCommand implements
        Command<CalculateErrorRatioCommandRequest, CalculateErrorRatioCommandResponse> {

    private static final Integer FIRST_CLUSTER = 1;
    private static final Integer DEFAULT_NUMBER_OF_ERROR = 0;

    @Override
    public CalculateErrorRatioCommandResponse execute(
            CalculateErrorRatioCommandRequest calculateErrorRatioCommandRequest) throws Exception {
        List<Double> errors = new ArrayList<>();
        List<List<Integer>> permutations = createPermutations(
                calculateErrorRatioCommandRequest.getRealClusters().size());

        permutations.forEach(permutation ->
                searchErrors(permutation, errors, calculateErrorRatioCommandRequest));

        Double minimumError = searchMinimumError(errors);

        return CalculateErrorRatioCommandResponse.builder()
                .errorRatio(minimumError)
                .build();
    }

    private List<List<Integer>> createPermutations(Integer size) {
        PermutationHelper permutationHelper = PermutationHelper.getInstance();
        List<Integer> labels = createLabels(size);

        return permutationHelper.generate(size, labels);
    }

    private List<Integer> createLabels(Integer size) {
        List<Integer> labels = new ArrayList<>();

        for (int i = FIRST_CLUSTER; i <= size; i++)
            labels.add(i);

        return labels;
    }

    private void searchErrors(List<Integer> permutations, List<Double> errors,
                              CalculateErrorRatioCommandRequest calculateErrorRatioCommandRequest) {
        AtomicInteger numberOfError = new AtomicInteger(DEFAULT_NUMBER_OF_ERROR);

        changeClusterId(permutations, calculateErrorRatioCommandRequest.getClusters());
        calculateErrorRatioCommandRequest.getRealClusters().forEach(cluster ->
                searchFromIris(numberOfError, cluster, calculateErrorRatioCommandRequest.getClusters()));

        Double error = calculateError(
                numberOfError.get(), calculateErrorRatioCommandRequest.getRealClusters().size());
        errors.add(error);
    }

    private void changeClusterId(List<Integer> permutations, List<Cluster> clusters) {
        for (int i = 0; i < clusters.size(); i++)
            clusters.get(i).setId(permutations.get(i));
    }

    private void searchFromIris(AtomicInteger numberOfError, Cluster cluster, List<Cluster> clusters) {
        cluster.getIrises().forEach(iris -> {
            Boolean isFound;

            try {
                isFound = checkIrisAndClusterId(cluster.getId(), iris, clusters);
            } catch (Exception e) {
                isFound = Boolean.FALSE;
            }

            if (!isFound)
                numberOfError.incrementAndGet();
        });
    }

    private Boolean checkIrisAndClusterId(Integer clusterId, Iris realIris, List<Cluster> clusters)
            throws Exception {
        Iris irisFounded = clusters.stream()
                .filter(cluster ->
                        isIdEquals(cluster.getId(), clusterId))
                .findFirst()
                .orElseThrow(Exception::new)
                .getIrises()
                .stream()
                .filter(iris ->
                        isIdEquals(iris.getId(), realIris.getId()))
                .findFirst()
                .orElse(null);

        return irisFounded != null;
    }

    private Boolean isIdEquals(Integer id, Integer searchId) {
        return id.equals(searchId);
    }

    private Double calculateError(Integer error, Integer dataSize) {
        return (double) error / dataSize;
    }

    private Double searchMinimumError(List<Double> errors) throws Exception {
        return errors.stream()
                .min(Comparator.comparingDouble(Double::doubleValue))
                .orElseThrow(Exception::new);
    }
}
