package com.mooo.swings.rectangle_rotation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

class Solution {

    static int rectangleRotation(final int a, final int b) {
        validateInput(a, b);
        Rectangle2D myRect = new Rectangle2D.Double(-a / 2, -b / 2, a, b);
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 4);
        Shape rotatedRect = at.createTransformedShape(myRect);

        return countPoints(rotatedRect);
    }

    private static void validateInput(int a, int b) {
        if (a > 10000 || b > 10000 || a < 2 || b < 2) {
            throw new IllegalArgumentException("Input not within constraints");
        }
    }

    private static int countPoints(Shape rotatedRect) {
        int points;
        points = getTopHalfPoints(rotatedRect) * 2;
        points += getMiddlePoints(rotatedRect);
        return points;
    }

    private static int getTopHalfPoints(Shape rotatedRect) {
        int points = 0;
        Rectangle bounds = rotatedRect.getBounds();
        for (int x = (int) bounds.getMinX(); x < bounds.getMaxX(); x++) {
            for (int y = 1; y < bounds.getMaxY(); y++) {
                if (rotatedRect.contains(x, y)) {
                    points++;
                }
            }
        }
        return points;
    }

    private static int getMiddlePoints(Shape rotatedRect) {
        int points = 0;
        Rectangle bounds = rotatedRect.getBounds();
        for (int x = (int) bounds.getMinX(); x < (int) bounds.getMaxX(); x++) {
            if (rotatedRect.contains(x, 0)) {
                points++;
            }

        }
        return points;
    }
}