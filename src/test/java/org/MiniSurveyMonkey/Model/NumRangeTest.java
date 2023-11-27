package org.MiniSurveyMonkey.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumRangeTest {

    @Test
    public void testNoArg() {
        NumRange nr = new NumRange();
        assertEquals(0, nr.getValue());
    }

    @Test
    public void testArgConstructor() {
        NumRange nr = new NumRange("Test Question", 1, 10);
        assertEquals(1, nr.getMinRange());
        assertEquals(10, nr.getMaxRange());
        assertEquals(0, nr.getValue());
    }


    @Test
    public void testSetValue() {
        NumRange nr = new NumRange();
        nr.setValue(7);
        assertEquals(7, nr.getValue());
    }
}

