package com.mooo.swings.spinwords;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by roelg on 15/03/2017.
 */
public class SpinWordsTest {
    SpinWords spinWords;

    @Before
    public void setUp() throws Exception {
        spinWords = new SpinWords();
    }
   @Test
    public void test() {
        assertEquals("emocleW", spinWords.spinWords("Welcome"));
        assertEquals("Hey wollef sroirraw", spinWords.spinWords("Hey fellow warriors"));
        assertEquals("Just gniddik ereht is llits one more", spinWords.spinWords("Just kidding there is still one more"));
    }


}