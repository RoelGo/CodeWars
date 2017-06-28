package com.mooo.swings.middle;

/**
 * Created by roelg on 15/03/2017.
 */
class Kata {
    public static String getMiddle(String word) {
        String output = "" + word.charAt(word.length()/2) ;
        if (word.length()%2 == 0){
            output = word.charAt(word.length()/2 -1) + output;
        }
        return output;
    }

    public static String SongDecoder (String song)
    {
        String[] songWords = song.split("WUB");
        String output = "";
        for (String songWord : songWords) {
            if (songWord.length() > 0) {
                output += songWord + " ";
            }
        }
        return output.trim();
    }

}