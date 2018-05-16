package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.model.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 15 May 2018
 */

@Data
@Builder
public class CalculateErrorRatioCommandResponse {

    private Double errorRatio;
}
