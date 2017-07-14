package com.mooo.swings.guesser;

/**
 * Created by roel on 14/07/17.
 */
public class LittleClass {

    static String o = "Roel Is cool";

    public static void isMySecret(String s) {
        if(s.equals(o)){
            System.out.println("Correct");
        }
        System.out.println("Try Again");
    }
}
