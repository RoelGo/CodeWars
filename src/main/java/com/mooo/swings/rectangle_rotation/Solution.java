package com.mooo.swings.rectangle_rotation;

import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Created by roel on 05/07/17.
 */
class Solution {

    static int rectangleRotation(final int a, final int b) {
        Rectangle2D myRect = new Rectangle2D.Double(-a/2, -b/2,a, b );
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 4);
        Shape rotatedRect = at.createTransformedShape(myRect);

        return countPoints(Math.max(a,b),rotatedRect);
    }

    private static int countPoints(int max, Shape rotatedRect) {
        int points = 0;
        for (int x = 0; x < max; x++) {
            for (int y = 0; y < max; y++) {
                if (rotatedRect.contains(x,y)){
                    points++;
                }
            }
        }
        return points;
    }
}