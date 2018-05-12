package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.Command;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.CalculateIrisesDistancesRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.IrisDistance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 12 May 2018
 */

public class CalculateIrisesDistancesCommand implements Command<CalculateIrisesDistancesRequest, Void> {

    @Override
    public Void execute(CalculateIrisesDistancesRequest calculateIrisesDistancesRequest) throws Exception {
        List<Iris> irises = calculateIrisesDistancesRequest.getIrises();
        List<Iris> irisesCopy = new ArrayList<>(irises);

        irises.forEach(iris ->
                calculateIrisDistances(iris, irisesCopy));

        return null; // Void object need null return value
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
