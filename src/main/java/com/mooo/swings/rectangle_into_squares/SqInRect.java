package com.mooo.swings.rectangle_into_squares;

import java.util.List;
import java.util.ArrayList;

import static java.lang.Math.min;

/**
 * Created by roel on 28/06/17.
 */
public class SqInRect {

    public static List<Integer> sqInRect(int lng, int wdth) {
        if (lng != wdth) {
            return calculateSquaresizes(lng, wdth);
        }
        return null;
    }


    /**TODO: use recursion
     * Define a single repeatable operation.
     *
    **/
    private static ArrayList<Integer> calculateSquaresizes(int lng, int wdth) {

        ArrayList<Integer> squareSizes = new ArrayList<>();

        int surface = lng * wdth;
        for (int squareSize = min(lng, wdth); surface > 0;) {
            int squareSurface = squareSize * squareSize;

            if (surface == 1){
                squareSizes.add(1);
                surface -= 1;
            }
            if (squareSurface < surface) {
                squareSizes.add(squareSize);
                surface -= squareSurface;
            }else {
                squareSize--;
            }
        }

        return squareSizes;
    }
}
