package com.mooo.swings.guesser;

import java.lang.reflect.Field;

import static java.lang.System.out;

public class Guesser {
    static void guess() {
        try {
            Class littleClass = LittleClass.class;
            Object littleObject = littleClass.newInstance();
            for (Field declaredField : littleClass.getDeclaredFields()) {
                declaredField.setAccessible(true);
                String guess = declaredField.get(littleObject).toString();
                out.println("Field Name: " + declaredField.getName());
                out.println("Field Value: " + guess);
                LittleClass.isMySecret(guess);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }
}