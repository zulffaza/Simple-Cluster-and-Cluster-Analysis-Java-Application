package com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.util;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.IrisDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

public class IrisesHelper {

    private static IrisesHelper instance;

    private IrisesHelper() {

    }

    public static IrisesHelper getInstance() {
        if (instance == null)
            instance = new IrisesHelper();

        return instance;
    }

    public void calculateIrisesDistances(List<Iris> irises) {
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
                iris.getSepalLength() - irisCopy.getSepalLength(),
                iris.getSepalWidth() - irisCopy.getSepalWidth(),
                iris.getPetalLength() - irisCopy.getPetalLength(),
                iris.getPetalWidth() - iris.getPetalWidth()).build();

        return calculateDistance(irisTemp);
    }

    private Double calculateDistance(Iris iris) {
        Double distance = Math.pow(iris.getSepalLength(), 2) +
                Math.pow(iris.getSepalWidth(), 2) +
                Math.pow(iris.getPetalLength(), 2) +
                Math.pow(iris.getPetalWidth(), 2);

        return Math.sqrt(distance);
    }
}
