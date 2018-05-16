package com.faza.example.simple.cluster.and.cluster.analysis.java.application.main;

import com.faza.example.simple.cluster.and.cluster.analysis.java.application.main.util.IrisHelper;
import com.faza.example.simple.cluster.and.cluster.analysis.java.application.model.Iris;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Faza Zulfika P P
 * @version 1.0.0
 * @since 11 May 2018
 */

public class IrisHelperTest {

    private IrisHelper irisHelper;

    @Before
    public void setUp() {
        irisHelper = IrisHelper.getInstance();
    }

    @Test
    public void readIrisDataSet_success() {
        String line1 = "1.1 1.2 1.3 1.4 1.5";
        String line2 = "2.1 2.2 2.3 2.4 2.5";

        List<String> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);

        Iris iris1 = Iris.builder(1, 1.1, 1.2, 1.3, 1.4).build();
        Iris iris2 = Iris.builder(2, 2.1, 2.2, 2.3, 2.4).build();

        List<Iris> results = irisHelper.readIrisDataSet(lines);

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals(iris1, results.get(0));
        assertEquals(iris2, results.get(1));
    }

    @Test
    public void readIrisDataSet_success_emptyList() {
        List<String> lines = Collections.emptyList();

        List<Iris> results = irisHelper.readIrisDataSet(lines);

        assertNotNull(results);
        assertEquals(0, results.size());
    }

    @Test(expected = NullPointerException.class)
    public void readIrisDataSet_failed_nullLines() {
        irisHelper.readIrisDataSet(null);
    }
}