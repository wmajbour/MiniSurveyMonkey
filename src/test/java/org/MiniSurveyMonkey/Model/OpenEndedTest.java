package org.MiniSurveyMonkey.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpenEndedTest {

    @Test
    public void testNoArg() {
        OpenEnded oe = new OpenEnded();
        assertEquals("", oe.getAnswers());
    }

    @Test
    public void testArgConstructor() {
        OpenEnded oe = new OpenEnded("Test Question");
        assertEquals("", oe.getAnswers());
    }

    @Test
    public void testSetAnswers() {
        OpenEnded oe = new OpenEnded();
        oe.setAnswers("Answer text");
        assertEquals("Answer text", oe.getAnswers());
    }
}

