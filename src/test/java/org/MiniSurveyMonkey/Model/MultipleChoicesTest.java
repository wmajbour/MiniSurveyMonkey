package org.MiniSurveyMonkey.Model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class MultipleChoicesTest {
    private MultipleChoice mcq;

    @BeforeEach
    public void setUp() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Mercedes");
        choices.add("BMW");
        choices.add("Honda");
        choices.add("Tesla");
        mcq = new MultipleChoice("Whats your favorite car?", choices);
    }

    @Test
    public void testCreation() {
        HashMap<String, Integer> choices = mcq.getChoices();
        assertNotNull("Choices should not be null", choices);
        assertEquals( 4, choices.size());
    }


    @Test
    public void testAddChoice() {
        mcq.addChoice("Mazda");
        HashMap<String, Integer> choices = mcq.getChoices();
        //"Choices should contain 'Mazda'",
        assertTrue( choices.containsKey("Mazda"));
        assertEquals( Integer.valueOf(1), choices.get("Mazda"));
    }


    @Test
    public void testSetChoices() {
        HashMap<String, Integer> newChoices = new HashMap<>();
        newChoices.put("New Option 1", 2);
        newChoices.put("New Option 2", 3);
        mcq.setChoices(newChoices);

        HashMap<String, Integer> choices = mcq.getChoices();
        //There should be 2 new choices
        assertEquals( 2, choices.size());
        assertTrue(choices.containsKey("New Option 1"));// Choices should contain 'New Option 1'
        assertEquals( Integer.valueOf(2), choices.get("New Option 1"));
    }
}



