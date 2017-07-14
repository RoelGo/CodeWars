package com.mooo.swings.guesser;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.util.AssertionErrors;

import static org.junit.Assert.*;

/**
 * Created by roel on 14/07/17.
 */
public class GuesserTest {

    @Test
    public void guess() throws Exception {
        Guesser.guess();
    }
}