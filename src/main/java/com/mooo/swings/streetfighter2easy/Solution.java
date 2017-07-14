package com.mooo.swings.streetfighter2easy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    static String[] streetFighterSelection(String[][] fighters, int[] position, String[] moves) {
        FighterSelector fighterSelector = new FighterSelector(fighters, position);
        return fighterSelector.insertMoves(moves);
    }
}

class FighterSelector {
    private final String[][] fighters;
    private Point position;
    private static final Map<String, Point> DIRECTION_MAP = generateDirectionMap();

    private static Map<String, Point> generateDirectionMap() {
        Map<String, Point> map = new HashMap<>();
        map.put("left", new Point(-1, 0));
        map.put("right", new Point(+1, 0));
        map.put("up", new Point(0, -1));
        map.put("down", new Point(0, +1));
        return map;
    }

    FighterSelector(String[][] fighters, int[] position) {
        this.fighters = fighters;
        this.position = new Point(position[0], position[1]);
    }

    String[] insertMoves(String[] moves) {
        List<String> output = new ArrayList<>();
        List<Point> coords = translateMoves(moves);
        for (Point coord : coords) {
            output.add(fighters[coord.y][coord.x]);
        }
        return output.toArray(new String[0]);
    }

    private List<Point> translateMoves(String[] moves) {
        List<Point> output = new ArrayList<>();
        for (String move : moves) {
            position = moveCursor(move);
            output.add(position);
        }
        return output;
    }

    private Point moveCursor(String move) {
        Point direction = DIRECTION_MAP.get(move);
        Point newPosition = position.getLocation();
        newPosition.translate(direction.x, direction.y);
        if (yOutOfBounds(newPosition)) {
            return position.getLocation();
        } else if(xOutOfBounds(newPosition)){
            return overflow(newPosition);
        }
        return newPosition;
    }

    private Point overflow(Point position) {
        return position.x < 0 ? new Point(fighters[0].length-1,position.y) :new Point(0,position.y)  ;
    }

    private boolean xOutOfBounds(Point position) {

        return position.x >= fighters[0].length || position.x < 0;
    }

    private boolean yOutOfBounds(Point position) {

        return position.y >= fighters.length || position.y < 0;
    }
}
