package com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.util;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

public class IrisesHelper {

    private static final Integer POW_NUMBER = 2;

    private static IrisesHelper instance;

    private IrisesHelper() {

    }

    public static IrisesHelper getInstance() {
        if (instance == null)
            instance = new IrisesHelper();

        return instance;
    }

    public Double calculateDistance(Iris iris, Iris irisCopy) {
        Iris irisTemp = Iris.builder(
                iris.getSepalLength() - irisCopy.getSepalLength(),
                iris.getSepalWidth() - irisCopy.getSepalWidth(),
                iris.getPetalLength() - irisCopy.getPetalLength(),
                iris.getPetalWidth() - irisCopy.getPetalWidth()).build();

        return calculateDistance(irisTemp);
    }

    private Double calculateDistance(Iris iris) {
        Double distance = Math.pow(iris.getSepalLength(), POW_NUMBER) +
                Math.pow(iris.getSepalWidth(), POW_NUMBER) +
                Math.pow(iris.getPetalLength(), POW_NUMBER) +
                Math.pow(iris.getPetalWidth(), POW_NUMBER);

        return Math.sqrt(distance);
    }
}
