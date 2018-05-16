package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 16 May 2018
 */

public class PermutationHelper {

    private static final Integer PERMUTATION_COMPLETED = 1;

    private static PermutationHelper instance;

    private PermutationHelper() {

    }

    public static PermutationHelper getInstance() {
        if (instance == null)
            instance = new PermutationHelper();

        return instance;
    }

    public List<List<Integer>> generate(Integer size, List<Integer> labels) {
        List<List<Integer>> permutations = new ArrayList<>();
        generate(size, labels, permutations);

        return permutations;
    }

    private void generate(Integer size, List<Integer> labels, List<List<Integer>> permutations) {
        if (size.equals(PERMUTATION_COMPLETED))
            copyObtainedPermutation(labels, permutations);
        else
            doGenerate(size, labels, permutations);
    }

    private void copyObtainedPermutation(List<Integer> labels, List<List<Integer>> permutations) {
        permutations.add(new ArrayList<>(labels));
    }

    private void doGenerate(Integer size, List<Integer> labels, List<List<Integer>> permutations) {
        Integer sizeMinusOne = size - 1;

        for (int i = 0; i < sizeMinusOne; i++) {
            generate(sizeMinusOne, labels, permutations);
            swapValue(getIndexToSwap(i, size), sizeMinusOne, labels);
        }

        generate(sizeMinusOne, labels, permutations);
    }

    private Boolean isEven(Integer size) {
        return size % 2 == 0;
    }

    private Integer getIndexToSwap(Integer iteration, Integer size) {
        return isEven(size) ? iteration : 0;
    }

    private void swapValue(Integer from, Integer to, List<Integer> labels) {
        Integer temp = labels.get(from);
        labels.set(from, labels.get(to));
        labels.set(to, temp);
    }
}
