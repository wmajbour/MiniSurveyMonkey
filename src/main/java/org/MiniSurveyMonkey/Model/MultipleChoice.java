package org.MiniSurveyMonkey.Model;

import java.util.HashMap;

/**
 * Class to store multiple choice style questions.
 */
public class MultipleChoice extends Question{

    /**
     * Hashmap that takes a string for the choice and a boolean to check if it is checked.
     * A minimum of one choice MUST be selected.
     * If none apply, have separate option; ex. "None of the above".
     */
    private HashMap<String, Boolean> choices;

    /**
     * Constructor for multiple choice.
     */
    public MultipleChoice(){
        super();
        this.choices = new HashMap<String, Boolean>();
    }

}
