package com.faza.example.simple.cluster.and.cluster.analysis.java.application.command.util;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 12 May 2018
 */

public class CommandExceptionHelper {

    private static final Integer EXIT_STATUS = -1;

    private static CommandExceptionHelper instance;

    private CommandExceptionHelper() {

    }

    public static CommandExceptionHelper getInstance() {
        if (instance == null)
            instance = new CommandExceptionHelper();

        return instance;
    }

    public void printMessage(String message) {
        System.out.println(message);
        System.exit(EXIT_STATUS);
    }
}
