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
    private static final QuestionType questionType = QuestionType.MULTIPLE_CHOICE;

    /**
     * Default constructor
     */
    public MultipleChoice() {
        super();
        this.choices = new HashMap<String,Integer>();
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

    public void addChoice(String choice){
        if (!this.choices.containsKey(choice)) {
            this.choices.put(choice, 1);
        } else {
            this.choices.put(choice, this.choices.get(choice) + 1);
        }
    }



    public QuestionType getQuestionType() {
        return questionType;
    }
}
