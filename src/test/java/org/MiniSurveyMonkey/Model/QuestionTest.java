package org.MiniSurveyMonkey.Model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    // Initializing an OpenEnded with a sample question
    OpenEnded op = new OpenEnded("What's your favorite color?");


    /**
     * Tests the setId and getId methods
     */
    @Test
    public void testId() {
        op.setId(1); // Setting the ID to 1
        assertEquals(1, op.getId(), "ID should be set and retrieved as 1");
    }

    /**
     * Tests the setQuestion and getQuestion methods
     */
    @Test
    public void testQuestion() {
        op.setQuestion("Is the question changed now?");
        assertEquals("Is the question changed now?", op.getQuestion(), "Question text should be updated");
    }
}

