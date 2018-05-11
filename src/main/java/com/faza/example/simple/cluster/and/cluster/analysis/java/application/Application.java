package com.faza.example.simple.cluster.and.cluster.analysis.java.application;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.CommandExecutor;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.implementation.CommandExecutorImpl;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation.ClusterCommand;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.ClusterRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response.ClusterResponse;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.util.FilesHelper;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.util.IrisHelper;

import java.io.InputStream;
import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 10 May 2018
 */

public class Application {

    private static final String DATASET_PATHNAME = "iris.txt";

    public static void main(String[] args) {
        // TODO run application logic here

        FilesHelper filesHelper = FilesHelper.getInstance();
        IrisHelper irisHelper = IrisHelper.getInstance();

        InputStream inputStream = filesHelper.getResourceFileStream(DATASET_PATHNAME);
        List<String> lines = filesHelper.readFileLines(inputStream);
        List<Iris> irises = irisHelper.readIrisDataSet(lines);

        CommandExecutor commandExecutor = CommandExecutorImpl.getInstance();

        try {
            ClusterResponse clusterResponse = commandExecutor.doExecute(
                    ClusterCommand.class, ClusterRequest.builder().irises(irises).build());
            clusterResponse.getClusters().forEach(cluster -> {
                System.out.println(cluster);

                cluster.getIrises().forEach(iris -> {
                    System.out.println("\t" + iris);

                    iris.getIrisDistances().forEach(irisDistance ->
                            System.out.println("\t\t" + irisDistance));
                });
            });
        } catch (Exception e) {
            System.out.println("Error running command...");
        }
    }
}
