package org.MiniSurveyMonkey.Model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumRangeTest {
    // Initializing a NumRange
    NumRange numericalRangeQuestion = new NumRange("Test1", 1, 10);

    /**
     * Tests the addAnswer method of NumRange.
     */
    @Test
    public void addAnswerTest() {
        assertTrue(numericalRangeQuestion.addAnswer("key1", 5), "Answer within range should return true");
        assertFalse(numericalRangeQuestion.addAnswer("key2", 11), "Answer outside range should return false");
    }

    /**
     * Tests if getMinRange method of NumRange returns the correct minimum range value.
     */
    @Test
    public void getMinRangeTest() {
        int min = numericalRangeQuestion.getMinRange();
        assertEquals(1, min, "Min range should match the initialized value");
    }

    /**
     * Tests if getMaxRange method of NumRange returns the correct maximum range value.
     */
    @Test
    public void getMaxRangeTest() {
        int max = numericalRangeQuestion.getMaxRange();
        assertEquals(10, max, "Max range should match  initialized value");
    }
}
