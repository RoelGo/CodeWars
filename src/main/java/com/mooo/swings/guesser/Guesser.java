package com.mooo.swings.guesser;

import java.lang.reflect.Field;

public class Guesser {
    static void guess() {
        for (Field field : LittleClass.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                LittleClass.isMySecret(field.get(null).toString());
            } catch (IllegalAccessException ignored) {
            }
        }

    }
}