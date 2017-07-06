package com.mooo.swings.rectangle_rotation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by roel on 05/07/17.
 */

public class SolutionTest {
    @Test
    public void BasicTests() {
        assertEquals(65, Solution.rectangleRotation(30, 2));
        assertEquals(23, Solution.rectangleRotation(6, 4));
        assertEquals(49, Solution.rectangleRotation(8, 6));
        System.out.println( Solution.rectangleRotation(10000, 10000));
        assertEquals(333, Solution.rectangleRotation(16, 20));
    }
}