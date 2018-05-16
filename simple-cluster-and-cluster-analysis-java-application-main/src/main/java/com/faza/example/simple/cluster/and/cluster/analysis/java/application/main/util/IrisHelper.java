package com.faza.example.simple.cluster.and.cluster.analysis.java.application.main.util;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class IrisHelper {

    private static final String WHITESPACE_REGEX = "\\s";

    private static final Integer IRIS_ID_INITIAL_VALUE = 1;
    private static final Integer SEPAL_LENGTH_INDEX = 0;
    private static final Integer SEPAL_WIDTH_INDEX = 1;
    private static final Integer PETAL_LENGTH_INDEX = 2;
    private static final Integer PETAL_WIDTH_INDEX = 3;

    private static IrisHelper instance;

    private IrisHelper() {

    }

    public static IrisHelper getInstance() {
        if (instance == null)
            instance = new IrisHelper();

        return instance;
    }

    public List<Iris> readIrisDataSet(List<String> lines) {
        AtomicInteger id = new AtomicInteger(IRIS_ID_INITIAL_VALUE);

        return lines.stream()
                .map(line -> buildIrisFromLine(id, line))
                .collect(Collectors.toList());
    }

    private List<Double> parseWordsToDoubles(String[] words) {
        return Arrays.stream(words)
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private Iris buildIrisFromLine(AtomicInteger id, String line) {
        List<Double> doubles = parseWordsToDoubles(
                line.split(WHITESPACE_REGEX));

        return Iris.builder(
                id.getAndIncrement(),
                doubles.get(SEPAL_LENGTH_INDEX),
                doubles.get(SEPAL_WIDTH_INDEX),
                doubles.get(PETAL_LENGTH_INDEX),
                doubles.get(PETAL_WIDTH_INDEX)).build();
    }

    public void printIrises(List<Iris> irises) {
        irises.forEach(iris ->
                System.out.println("\t" + iris));
    }
}
