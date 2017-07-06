package com.mooo.swings.rectangle_rotation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

class Solution {

    static int rectangleRotation(final int a, final int b) {
        Rectangle2D myRect = new Rectangle2D.Double(-a / 2, -b / 2, a, b);
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 4);
        Shape rotatedRect = at.createTransformedShape(myRect);

        return countPoints(rotatedRect);
    }

    private static int countPoints(Shape rotatedRect) {
        int points = 0;
        Rectangle bounds = rotatedRect.getBounds();
        for (int x = (int) bounds.getMinX(); x < bounds.getMaxX(); x++) {
            for (int y = (int) bounds.getMinY(); y < bounds.getMaxY(); y++) {
                if (rotatedRect.contains(x, y)) {
                    points++;
                }
            }
        }
        return points;
    }
}