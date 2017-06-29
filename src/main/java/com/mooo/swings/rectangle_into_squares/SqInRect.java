package com.mooo.swings.rectangle_into_squares;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

/**
 * Created by roel on 28/06/17.
 */
public class SqInRect {

    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng != wdth) {
            ArrayList<Integer> squareSizes = new ArrayList<>();
            return calculateSquareSizes(lng, wdth, squareSizes);
        }
        return null;
    }
    
    private static ArrayList<Integer> calculateSquareSizes(int lng, int wdth, ArrayList<Integer> squareSizes) {
        int squareSize = min(lng, wdth);
        if (lng == wdth) {
            squareSizes.add(squareSize);
            return squareSizes;
        }
        if (lng > wdth) {
            lng -= squareSize;
        } else {
            wdth -= squareSize;
        }
        squareSizes.add(squareSize);
        return calculateSquareSizes(lng, wdth, squareSizes);
    }
}
