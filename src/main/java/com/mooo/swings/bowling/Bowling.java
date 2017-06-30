package com.mooo.swings.bowling;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

class Bowling {

    private Field field;

    Bowling() {
        this.field = new Field();
    }

    String bowlingPins(int[] arr) {
        field = new Field();
        field.removePins(arr);
        return field.toString();
    }

    public class Field {

        private List<Pin> pins;

        Field() {
            pins = new ArrayList<>();
            initializePins();
        }

        private void initializePins() {
            for (int pinNumber = 1; pinNumber <= 10; pinNumber++) {
                pins.add(new Pin(pinNumber));
            }
        }

        void removePins(int[] pinsToRemove) {
            pins.stream()
                    .filter(pin -> IntStream.of(pinsToRemove).anyMatch(pinToRemove -> pinToRemove == pin.getPinNumber()))
                    .forEach(Pin::kill);
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (int row = 1; row <= 4; row++) {
                for (int column = 1; column <= 7; column++) {
                    int finalRow = row;
                    int finalColumn = column;
                    Optional<Pin> optionalPin = pins.stream()
                            .filter(pin -> pin.getLocation().equals(new Point(finalRow, finalColumn)))
                            .findFirst();
                    if (optionalPin.isPresent()) {
                        output.append(optionalPin
                                .get()
                                .isLife() ? "I" : " ");
                    } else {
                        output.append(" ");
                    }
                }
                if (row != 4) {
                    output.append("\n");
                }
            }
            return output.toString();
        }
    }

    public class Pin {

        private int pinNumber;
        private Point location;
        private boolean life;

        Pin(int pinNumber) {
            this.pinNumber = pinNumber;
            this.life = true;
            calculateLocation();
        }

        private void calculateLocation() {
            int x = 0;
            int y = 0;

            if (pinNumber <= 1) {
                x = 4;
                y = 4;
            } else if (pinNumber <= 3) {
                x = 3;
                y = (pinNumber - 2) * 2 + 3;
            } else if (pinNumber <= 6) {
                x = 2;
                y = (pinNumber - 4) * 2 + 2;
            } else if (pinNumber <= 10) {
                x = 1;
                y = (pinNumber - 7) * 2 + 1;
            }

            if (x == 0 || y == 0) {
                throw new RuntimeException("I can't do math");
            }


            location = new Point(x, y);
        }

        boolean isLife() {
            return life;
        }

        int getPinNumber() {
            return pinNumber;
        }

        Point getLocation() {
            return location;
        }

        void kill() {
            life = false;
        }
    }
}
