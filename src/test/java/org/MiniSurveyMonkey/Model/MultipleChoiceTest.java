package org.MiniSurveyMonkey.Model;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class MultipleChoiceTest {

    //no-argument constructor and ensures the choices set is empty.
    @Test
    public void testisEmpty() {
        MultipleChoice mc = new MultipleChoice();
        assertTrue(mc.getChoices().isEmpty());
    }

    //Tests the constructor with arguments (question and choices)
    @Test
    public void testArgConstructor() {
        Set<Choice> choices = new HashSet<>();
        choices.add(new Choice("Choice 1", false));
        MultipleChoice mc = new MultipleChoice("Test Question", choices);
        assertEquals(1, mc.getChoices().size());
    }
    //Tests adding a single Choice
    @Test
    public void testAddChoice() {
        MultipleChoice mc = new MultipleChoice();
        mc.addChoice(new Choice("New Choice", true));
        assertEquals(1, mc.getChoices().size());
    }
    //Tests setting a new set of choices , checks size
    @Test
    public void testSetChoices() {
        MultipleChoice mc = new MultipleChoice();
        Set<Choice> newChoices = new HashSet<>();
        newChoices.add(new Choice("Choice 1", true));
        newChoices.add(new Choice("Choice 2", false));
        mc.setChoices(newChoices);
        assertEquals(2, mc.getChoices().size());
    }
}

