package com.faza.example.simple.cluster.and.cluster.analysis.java.application.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 10 May 2018
 */

@Getter
@Setter
@EqualsAndHashCode
public class Iris {

    private Integer id;

    private Double sepalLength;
    private Double sepalWidth;
    private Double petalLength;
    private Double petalWidth;

    private List<IrisDistance> irisDistances;

    private Iris(IrisBuilder irisBuilder) {
        this.id = irisBuilder.id;

        this.sepalLength = irisBuilder.sepalLength;
        this.sepalWidth = irisBuilder.sepalWidth;
        this.petalLength = irisBuilder.petalLength;
        this.petalWidth = irisBuilder.petalWidth;

        this.irisDistances = irisBuilder.irisDistances;

        sortIrisDistance();
    }

    public static class IrisBuilder {

        private Integer id;

        private Double sepalLength;
        private Double sepalWidth;
        private Double petalLength;
        private Double petalWidth;

        private List<IrisDistance> irisDistances = new ArrayList<>();

        private IrisBuilder() {
            this(0, 0.0, 0.0, 0.0, 0.0);
        }

        private IrisBuilder(Integer id, Double sepalLength, Double sepalWidth, Double petalLength, Double petalWidth) {
            this.id = id;

            this.sepalLength = sepalLength;
            this.sepalWidth = sepalWidth;
            this.petalLength = petalLength;
            this.petalWidth = petalWidth;
        }

        public IrisBuilder setIrisDistances(List<IrisDistance> irisDistances) {
            this.irisDistances = irisDistances;
            return this;
        }

        public Iris build() {
            return new Iris(this);
        }
    }

    public static IrisBuilder builder() {
        return new IrisBuilder();
    }

    public static IrisBuilder builder(Integer id, Double sepalLength, Double sepalWidth, Double petalLength, Double petalWidth) {
        return new IrisBuilder(id, sepalLength, sepalWidth, petalLength, petalWidth);
    }

    public void setIrisDistances(List<IrisDistance> irisDistances) {
        this.irisDistances = irisDistances;
        sortIrisDistance();
    }

    public void addIrisDistance(IrisDistance irisDistance) {
        this.irisDistances.add(irisDistance);
        sortIrisDistance();
    }

    public IrisDistance getIrisDistance(Integer id) {
        return this.irisDistances.stream()
                .filter(irisDistance ->
                        isIdEquals(irisDistance.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private void sortIrisDistance() {
        this.irisDistances.sort(
                Comparator.comparingInt(IrisDistance::getId));
    }

    private Boolean isIdEquals(Integer id, Integer searchId) {
        return id.equals(searchId);
    }

    @Override
    public String toString() {
        return "Iris-" + this.id;
    }
}
