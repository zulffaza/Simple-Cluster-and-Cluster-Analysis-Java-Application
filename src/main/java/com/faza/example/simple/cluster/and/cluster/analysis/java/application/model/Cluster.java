package com.faza.example.simple.cluster.and.cluster.analysis.java.application.model;

import lombok.AccessLevel;
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
public class Cluster {

    private Integer id;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Boolean needCentroid;

    @Setter(AccessLevel.NONE)
    private Iris centroid;

    private List<Iris> irises;
    private List<ClusterDistance> clusterDistances;

    private Cluster(ClusterBuilder clusterBuilder) {
        this.id = clusterBuilder.id;

        this.needCentroid = clusterBuilder.needCentroid;

        this.irises = clusterBuilder.irises;
        this.clusterDistances = clusterBuilder.clusterDistances;

        sortIrises();
        sortClusterDistances();

        buildCentroid();
    }

    public static class ClusterBuilder {

        private Integer id;

        private Boolean needCentroid = Boolean.FALSE;

        private List<Iris> irises;
        private List<ClusterDistance> clusterDistances = new ArrayList<>();

        private ClusterBuilder(Integer id, List<Iris> irises) {
            this.id = id;

            this.irises = irises;
        }

        public ClusterBuilder needCentroid() {
            needCentroid = Boolean.TRUE;
            return this;
        }

        public ClusterBuilder setClusterDistances(List<ClusterDistance> clusterDistances) {
            this.clusterDistances = clusterDistances;
            return this;
        }

        public Cluster build() {
            return new Cluster(this);
        }
    }

    public static ClusterBuilder builder(Integer id, List<Iris> irises) {
        return new ClusterBuilder(id, irises);
    }

    public void setIrises(List<Iris> irises) {
        this.irises = irises;
        sortIrises();

        buildCentroid();
    }

    public void addIris(Iris iris) {
        this.irises.add(iris);
        sortIrises();

        buildCentroid();
    }

    public Iris getIris(Integer id) {
        return this.irises.stream()
                .filter(iris ->
                        isIdEquals(iris.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public void setClusterDistances(List<ClusterDistance> clusterDistances) {
        this.clusterDistances = clusterDistances;
        sortClusterDistances();
    }

    public void addClusterDistance(ClusterDistance clusterDistance) {
        this.clusterDistances.add(clusterDistance);
        sortClusterDistances();
    }

    public ClusterDistance getClusterDistance(Integer id) {
        return this.clusterDistances.stream()
                .filter(clusterDistance ->
                        isIdEquals(clusterDistance.getId(), id))
                .findFirst()
                .orElse(null);
    }

    private void buildCentroid() {
        if (this.needCentroid) {
            this.centroid = Iris.builder().build();

            sumAllIrisesField();
            divideFieldWithIrisesSize();
        }
    }

    private void sumAllIrisesField() {
        this.irises.forEach(iris -> {
            this.centroid.setSepalLength(this.centroid.getSepalLength() + iris.getSepalLength());
            this.centroid.setSepalWidth(this.centroid.getSepalWidth() + iris.getSepalWidth());
            this.centroid.setPetalLength(this.centroid.getPetalLength() + iris.getPetalLength());
            this.centroid.setPetalWidth(this.centroid.getPetalWidth() + iris.getPetalWidth());
        });
    }

    private void divideFieldWithIrisesSize() {
        Integer irisesSize = this.irises.size();

        this.centroid.setSepalLength(this.centroid.getSepalLength() / irisesSize);
        this.centroid.setSepalWidth(this.centroid.getSepalWidth() / irisesSize);
        this.centroid.setPetalLength(this.centroid.getPetalLength() / irisesSize);
        this.centroid.setPetalWidth(this.centroid.getPetalWidth() / irisesSize);
    }

    private void sortIrises() {
        this.irises.sort(
                Comparator.comparingInt(Iris::getId));
    }

    private void sortClusterDistances() {
        this.clusterDistances.sort(
                Comparator.comparingInt(ClusterDistance::getId));
    }

    private Boolean isIdEquals(Integer id, Integer searchId) {
        return id.equals(searchId);
    }

    @Override
    public String toString() {
        return "Cluster-" + this.id;
    }
}
