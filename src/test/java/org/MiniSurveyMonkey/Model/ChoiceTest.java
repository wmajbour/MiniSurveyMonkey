package org.MiniSurveyMonkey.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for the Choice entity
public class ChoiceTest {


    @Test
    public void testNoArgConstructor() {
        Choice choice = new Choice();

        // Assert that the ID is null (since it's not set)
        assertNull(choice.getId());

        // Assert that the choice string is empty
        assertEquals("", choice.getChoice());

        // Assert that the selected flag is false by default
        assertFalse(choice.getSelected());
    }

    @Test
    public void testArgConstructor() {
        // Create a new Choice object with specific values
        Choice choice = new Choice("Test Choice", true);

        // Assert that the choice string is set correctly
        assertEquals("Test Choice", choice.getChoice());

        // Assert that the selected flag is set correctly
        assertTrue(choice.getSelected());
    }

    // Test setting and getting ID
    @Test
    public void testSetAndGetId() {
        Choice choice = new Choice();

        // Set the ID and verify it's set correctly
        choice.setId(1L);
        assertEquals(1L, choice.getId());
    }

    // Test setting and getting the choice string
    @Test
    public void testSetAndGetChoice() {
        Choice choice = new Choice();
        // Set the choice string and verify it's set correctly
        choice.setChoice("New Choice");
        assertEquals("New Choice", choice.getChoice());
    }

    // Test setting and getting the selected flag
    @Test
    public void testSetAndGetSelected() {
        Choice choice = new Choice();

        // Set the selected flag and verify it's set correctly
        choice.setSelected(true);
        assertTrue(choice.getSelected());
    }
}
