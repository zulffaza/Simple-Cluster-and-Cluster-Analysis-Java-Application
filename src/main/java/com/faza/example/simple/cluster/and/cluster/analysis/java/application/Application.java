package com.faza.example.simple.cluster.and.cluster.analysis.java.application;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.CommandExecutor;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.implementation.CommandExecutorImpl;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation.ClusterCommand;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.ClusterCommandRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response.ClusterCommandResponse;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.util.CommandExceptionHelper;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.implementation.CentroidLinkageStrategy;
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
    private static final Integer NUMBER_OF_CLUSTER = 3;


    public static void main(String[] args) {
        FilesHelper filesHelper = FilesHelper.getInstance();
        IrisHelper irisHelper = IrisHelper.getInstance();

        InputStream inputStream = filesHelper.getResourceFileStream(DATASET_PATHNAME);
        List<String> lines = filesHelper.readFileLines(inputStream);
        List<Iris> irises = irisHelper.readIrisDataSet(lines);

        CommandExecutor commandExecutor = CommandExecutorImpl.getInstance();
        ClusterCommandRequest clusterCommandRequest = ClusterCommandRequest.builder()
                .irises(irises)
                .clusterStrategy(
                        CentroidLinkageStrategy.getInstance())
                .numberOfCluster(NUMBER_OF_CLUSTER)
                .build();

        try {
            ClusterCommandResponse clusterCommandResponse = commandExecutor.doExecute(
                    ClusterCommand.class, clusterCommandRequest);
            clusterCommandResponse.getClusters().forEach(cluster ->
                    System.out.println(cluster + " : " + cluster.getIrises().size()));
        } catch (Exception e) {
            e.printStackTrace();

            CommandExceptionHelper.getInstance()
                    .printMessage("Error while running ClusterCommand...");
        }
    }
}
