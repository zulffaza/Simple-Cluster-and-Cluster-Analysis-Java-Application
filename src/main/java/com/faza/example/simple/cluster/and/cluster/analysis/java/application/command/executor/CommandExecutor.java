package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.executor;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.Command;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public interface CommandExecutor {

    <REQUEST, RESPONSE> RESPONSE doExecute(
            Class<? extends Command<REQUEST, RESPONSE>> commandClass, REQUEST request) throws Exception;
}
