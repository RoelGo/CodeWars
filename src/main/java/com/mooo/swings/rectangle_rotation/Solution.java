package com.mooo.swings.rectangle_rotation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

class Solution {

    static int rectangleRotation(final int a, final int b) {
        validateInput(a, b);
        Shape rotatedRect = calculateRotatedRectangle(a, b);

        return calculatePoints(rotatedRect);
//        return countPoints(rotatedRect);
    }

    private static Shape calculateRotatedRectangle(int a, int b) {
        Rectangle2D myRect = new Rectangle2D.Double(-a / 2, -b / 2, a, b);
        AffineTransform at = AffineTransform.getRotateInstance(PI / 4);
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

        return tom.getSuccessfulSteps();
    }

    private static void validateInput(int a, int b) {
        if (a > 10000 || b > 10000 || a < 2 || b < 2) {
            throw new IllegalArgumentException("Input not within constraints");
        }
    }

}

class Hiker {
    private final Shape hikeArea;
    private final String name;
    private Point nextStep;
    private Direction direction = Direction.DOWNISH;
    private int failedSteps = 0;
    private int successfulSteps = 0;
    private boolean stop;
    private Direction previousDirection = null;
    Hiker(Shape hikeArea, String name) {
        this.hikeArea = hikeArea;
        this.nextStep = calculateStart();
        this.name = name;
    }

    private static Point getNextPoint(PathIterator pathIterator) {
        double[] cords = new double[2];
        pathIterator.currentSegment(cords);
        Point point = new Point((int) ceil(cords[0]), (int) floor(cords[1]));
        pathIterator.next();
        return point;
    }

    public int getSuccessfulSteps() {
        return successfulSteps;
    }

    private Point calculateStart() {
        List<Point> pointList = new ArrayList<>();
        PathIterator pathIterator = hikeArea.getPathIterator(null);
        for (int pointNumber = 0; pointNumber < 4; pointNumber++) {
            pointList.add(getNextPoint(pathIterator));
        }
        return pointList.get(2);// should be top point
    }

    void takeAHikeMate() {
        while (!stop) {
            Point currentStep = nextStep;
            if (thisGoesNowhere()) {
                failedSteps++;
                currentStep = direction.showHikerBack(currentStep);
                changeDirection(Direction.RIGHT);
                System.out.println("Hiker " + name + " took a failed step,\n" +
                        "They are at ( " + currentStep.x + " , " + currentStep.y + " ) right now,\n" +
                        "They took " + failedSteps + " failed steps so far\n");

                if (failedSteps > 1) {
                    itsOver();
                }
            } else {
                successfulSteps++;
                failedSteps = 0;
                System.out.println("Hiker " + name + " took a successful step,\n" +
                        "They are at ( " + currentStep.x + " , " + currentStep.y + " ) right now,\n" +
                        "They took " + successfulSteps + " successful steps so far\n");
                changeDirectionIfGoingRight();
            }
            nextStep = direction.showHikerNextStep(currentStep);
        }
    }

    private void changeDirection(Direction direction) {
        previousDirection = this.direction;
        this.direction = direction;
    }

    private void changeDirectionIfGoingRight() {
        if (direction.equals(Direction.RIGHT)) {
            if (previousDirection.equals(Direction.DOWNISH)) {
                changeDirection(Direction.UPISH);
            } else {
                changeDirection(Direction.DOWNISH);
            }
        }
    }

    private void itsOver() {
        stop = true;
    }

    private boolean thisGoesNowhere() {
        return !hikeArea.contains(nextStep);
    }
}

class Direction {
    static final Direction DOWNISH = new Direction(-1, -1);
    static final Direction UPISH = new Direction(1, 1);
    static final Direction RIGHT = new Direction(1, 0);
    private final int xDirection;
    private final int yDirection;

    public Direction(int xDirection, int yDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
    }

    public Point showHikerNextStep(Point currentStep) {
        return new Point(currentStep.x + xDirection, currentStep.y + yDirection);
    }

    public Point showHikerBack(Point currentStep) {
        return new Point(currentStep.x - xDirection, currentStep.y - yDirection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        if (xDirection != direction.xDirection) return false;
        return yDirection == direction.yDirection;
    }

    @Override
    public int hashCode() {
        int result = xDirection;
        result = 31 * result + yDirection;
        return result;
    }
}
