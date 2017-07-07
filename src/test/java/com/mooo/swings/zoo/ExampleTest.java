package com.mooo.swings.zoo;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ExampleTest {

    @Test
    public void example() {
        final String input = "fox,bug,chicken,grass,sheep";
        final String[] expected = {
                "fox,bug,chicken,grass,sheep",
                "chicken eats bug",
                "fox eats chicken",
                "sheep eats grass",
                "fox eats sheep",
                "fox"};
        assertArrayEquals(expected, Dinglemouse.whoEatsWho(input));
    }    @Test
    public void example3() {
        final String input = "chicken,grass,panda,cow,grass,busker,grass,banana,banana,little-fish";
        final String[] expected = {
                "chicken,grass,panda,cow,grass,busker,grass,banana,banana,little-fish",
                "cow eats grass",
                "chicken,grass,panda,cow,busker,grass,banana,banana,little-fish"};
        String[] actuals = Dinglemouse.whoEatsWho(input);
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void example2() {
        final String input = "big-fish,banana,little-fish,lion,chicken,lion,leaves,lion,busker,bicycle,big-fish,banana,little-fish,lion,chicken,lion,leaves,lion,busker,bicycle";

        System.out.println(Arrays.toString(Dinglemouse.whoEatsWho(input)));
    }

}