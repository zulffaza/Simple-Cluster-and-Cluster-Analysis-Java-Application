package com.faza.example.simple.cluster.and.cluster.analysis.java.application.main;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.main.util.FilesHelper;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class FilesHelperTest {

    private FilesHelper filesHelper;

    @Before
    public void setUp() {
        filesHelper = FilesHelper.getInstance();
    }

    @Test
    public void getResourceFileStream_success() {
        assertNotNull(
                filesHelper.getResourceFileStream("mock.txt"));
    }

    @Test
    public void getResourceFileStream_failed_fileNotFound() {
        assertNull(
                filesHelper.getResourceFileStream("undefined.txt"));
    }

    @Test
    public void readFileLines_success() {
        byte[] bytes = generateRandomByteArray();

        assertNotNull(
                filesHelper.readFileLines(
                        new ByteArrayInputStream(bytes)));
    }

    private byte[] generateRandomByteArray() {
        byte[] bytes = new byte[20];
        new Random().nextBytes(bytes);

        return bytes;
    }
}