package com.mooo.swings.spinwords;

/**
 * Created by roelg on 15/03/2017.
 */
public class SpinWords {

    public String spinWords(String sentence) {
        String[] words = sentence.split(" ");
        String output = "";
        for (String word : words) {
            if (word.length() >= 5) {
                output += new StringBuilder(word).reverse().toString();
            }else {
                output += word;
            }
            output += " ";
        }
        return output.trim();
    }
}