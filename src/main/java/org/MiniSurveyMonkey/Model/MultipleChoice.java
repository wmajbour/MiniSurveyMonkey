package org.MiniSurveyMonkey.Model;

import java.util.ArrayList;
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
    private HashMap<String, Integer> choices;

    /**
     * Constructor for multiple choice.
     */
    public MultipleChoice(){
        super();
        this.choices = new HashMap<String, Integer>();
    }


    public HashMap<String, Integer> getChoices() {
        return choices;
    }

    public void setChoices(HashMap<String, Integer> choices) {
        this.choices = choices;
    }

    public MultipleChoice(String question, ArrayList<String> choices){
        super(question);
        this.choices = new HashMap<String, Integer>();
        for(String choice: choices){
            this.choices.put(choice,0);
        }
    }
    /**
     * Adds a choice to the choices HashMap.
     * If the choice is new, it initializes its count to 1, If the choice already exists, it increments the existing count by 1.
     *
     * @param choice The choice to be added or incremented in the choices map.
     */
    public void addChoice(String choice) {
        // Check if the choices map already contains the given choice.
        if (!this.choices.containsKey(choice)) {
            // If the choice is new, add it to the map with a count of 1.
            this.choices.put(choice, 1);
        } else {
            // If the choice exists, increment its count by 1.
            this.choices.put(choice, this.choices.get(choice) + 1);
        }
    }


}
