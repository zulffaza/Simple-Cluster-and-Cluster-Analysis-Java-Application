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
    private static final Integer MINUS_NUMBER = 1;
    private static final Integer FIRST_INDEX = 0;
    private static final Integer MODULUS_NUMBER = 2;
    private static final Integer EVEN_RESULT = 0;

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
        Integer sizeMinusOne = size - MINUS_NUMBER;

        for (Integer i = FIRST_INDEX; i < sizeMinusOne; i++) {
            generate(sizeMinusOne, labels, permutations);
            swapValue(getIndexToSwap(i, size), sizeMinusOne, labels);
        }

        generate(sizeMinusOne, labels, permutations);
    }

    private Boolean isEven(Integer size) {
        return size % MODULUS_NUMBER == EVEN_RESULT;
    }

    private Integer getIndexToSwap(Integer iteration, Integer size) {
        return isEven(size) ? iteration : FIRST_INDEX;
    }

    private void swapValue(Integer from, Integer to, List<Integer> labels) {
        Integer temp = labels.get(from);
        labels.set(from, labels.get(to));
        labels.set(to, temp);
    }
}
