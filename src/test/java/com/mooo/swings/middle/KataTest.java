package com.mooo.swings.middle;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by roelg on 15/03/2017.
 */
public class KataTest {

    @Test
    public void evenTests() {
        assertEquals("es", Kata.getMiddle("test"));
        assertEquals("dd", Kata.getMiddle("middle"));
    }

    @Test
    public void oddTests() {
        assertEquals("t", Kata.getMiddle("testing"));
        assertEquals("A", Kata.getMiddle("A"));
    }

    @Test
    public void Test1() {
        assertEquals("ABC", Kata.SongDecoder("WUBWUBABCWUB"));
    }
    @Test
    public void Test2()
    {
        assertEquals("R L",Kata.SongDecoder("RWUBWUBWUBLWUB"));
    }
}