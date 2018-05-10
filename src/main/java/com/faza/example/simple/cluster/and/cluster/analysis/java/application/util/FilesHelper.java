package com.faza.example.simple.cluster.and.cluster.analysis.java.application.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 10 May 2018
 */

public class FilesHelper {

    private static FilesHelper instance;

    private FilesHelper() {

    }

    public static FilesHelper getInstance() {
        if (instance == null)
            instance = new FilesHelper();

        return instance;
    }

    public InputStream getResourceFileStream(String pathname) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(pathname);
    }

    public List<String> readFileLines(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        return bufferedReader.lines()
                .collect(Collectors.toList());
    }
}
