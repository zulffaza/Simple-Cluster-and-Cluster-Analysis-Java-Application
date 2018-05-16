package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Cluster;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

@Data
@Builder
public class ClusterCommandResponse {

    private List<Cluster> clusters;
}
