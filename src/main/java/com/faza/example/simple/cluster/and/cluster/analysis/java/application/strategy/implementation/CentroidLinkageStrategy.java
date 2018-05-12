package com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.implementation;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.CommandExecutor;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor.implementation.CommandExecutorImpl;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.implementation.CalculateIrisesDistancesCommand;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.request.CalculateIrisesDistancesRequest;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.util.CommandExceptionHelper;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.ClusterStrategy;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.strategy.model.request.ClusterStrategyRequest;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class CentroidLinkageStrategy implements ClusterStrategy {

    private static CentroidLinkageStrategy instance;

    private CentroidLinkageStrategy() {

    }

    public static CentroidLinkageStrategy getInstance() {
        if (instance == null)
            instance = new CentroidLinkageStrategy();

        return instance;
    }

    @Override
    public Void execute(ClusterStrategyRequest clusterStrategyRequest) {
        CommandExecutor commandExecutor = CommandExecutorImpl.getInstance();
        CalculateIrisesDistancesRequest calculateIrisesDistancesRequest = CalculateIrisesDistancesRequest.builder()
                .irises(
                        clusterStrategyRequest.getIrises())
                .build();

        try {
            commandExecutor.doExecute(
                    CalculateIrisesDistancesCommand.class, calculateIrisesDistancesRequest);
        } catch (Exception e) {
            CommandExceptionHelper.getInstance()
                    .printMessage("Error while running CalculateIrisesDistancesCommand...");
        }

        // TODO create down clusters
        // TODO build centroid for all clusters
        // TODO

        return null; // Void object need null return value
    }
}
