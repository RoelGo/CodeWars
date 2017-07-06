package com.mooo.swings.rectangle_rotation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class Solution {

    static int rectangleRotation(final int a, final int b) {
        validateInput(a, b);
        Shape rotatedRect = calculateRotatedRectangle(a, b);

        return calculatePoints(rotatedRect);
//        return countPoints(rotatedRect);
    }

    private static Shape calculateRotatedRectangle(int a, int b) {
        Rectangle2D myRect = new Rectangle2D.Double(-a / 2, -b / 2, a, b);
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 4);
        return at.createTransformedShape(myRect);
    }

    /**
     * How about this:
     * <p>
     * Find top point
     * move down util not in there
     * move right (x) (might be dependant on evenness off the thing or not ugh)
     * move up until not there
     * rinse and repeat until left is not there then u done?
     * <p>
     * I hate this kata
     **/

    private static int calculatePoints(Shape rotatedRect) {
        Hiker tom = new Hiker(rotatedRect, "Tom");

        tom.takeAHikeMate();

        return 0;
    }

    private static void validateInput(int a, int b) {
        if (a > 10000 || b > 10000 || a < 2 || b < 2) {
            throw new IllegalArgumentException("Input not within constraints");
        }
    }

    private static class Hiker {
        private final Point2D start;
        private final Shape hikeArea;
        private final String name;

        public Hiker(Shape hikeArea, String name) {
            this.start = calculateStart();
            this.hikeArea = hikeArea;
            this.name = name;
        }

        private static Point2D.Double getNextPoint(PathIterator pathIterator) {
            double[] cords = new double[2];
            pathIterator.currentSegment(cords);
            Point2D.Double point = new Point2D.Double(Math.ceil(cords[0]), Math.floor(cords[1]));
            pathIterator.next();
            return point;
        }

        private Point2D calculateStart() {
            List<Point2D.Double> pointList = new ArrayList<>();
            PathIterator pathIterator = hikeArea.getPathIterator(null);
            for (int pointNumber = 0; pointNumber < 4; pointNumber++) {
                pointList.add(getNextPoint(pathIterator));
            }
            return pointList.get(2);// should be top point
        }

        public void takeAHikeMate() {
            Point2D nextStep = start;

            if (thisGoesSomewhere()) {

            }

        }

        private boolean thisGoesSomewhere() {
            return false;
        }

    }
}