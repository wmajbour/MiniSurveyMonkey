package org.MiniSurveyMonkey.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenEndedTest {
    private OpenEnded openEndedQuestion;
    @BeforeEach
    public void setUp() {
        openEndedQuestion = new OpenEnded("What is your favorite color?");
    }

    @Test
    public void testQuestionInitialization() {
        // Testing if the question is correctly initialized
        assertEquals("What is your favorite color?", openEndedQuestion.getQuestion(), "Question should be initialized correctly");
    }

    @Test
    public void testSetAndGetAnswers() {
        // Setting an answer and testing if it is correctly set
        openEndedQuestion.setAnswers("Blue");
        assertEquals("Blue", openEndedQuestion.getAnswers(), "Answer should be 'Blue'");

        // Changing the answer and testing the change
        openEndedQuestion.setAnswers("Green");
        assertEquals("Green", openEndedQuestion.getAnswers(), "Answer should now be 'Green'");
    }
}

