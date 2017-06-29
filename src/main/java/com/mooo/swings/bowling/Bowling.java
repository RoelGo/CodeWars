package com.mooo.swings.bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {

    private Field field;

    public Bowling() {
        this.field = new Field();
    }

    public String bowlingPins(int[] arr) {

        String output = null;


        return output;
    }


    public class Field {

        private Map<Row> rows;




        public Field() {
            rows = new ArrayList<>();
            initializeRows();
        }

        private void initializeRows() {
            for (int i = 1; i <= 4; i++) {
                rows.add(new Row(i));
            }
        }
Jino0[XowoSoxa
    }

    public class Row {
        private List<Pin> pins = new ArrayList<>();
        private int index;

        public Row(int index) {
            this.index = index;
            initializePins();
        }

        private void initializePins() {
            for (int i = 0; i < index; i++) {
                pins.add(new Pin());
            }
        }
    }


    public class Pin {

        private int pinNumber;
        private boolean life;

        public Pin(int pinNumber) {
            this.pinNumber = pinNumber;
        }



    }


}

