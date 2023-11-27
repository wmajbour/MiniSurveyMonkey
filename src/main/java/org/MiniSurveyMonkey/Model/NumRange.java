package org.MiniSurveyMonkey.Model;

import jakarta.persistence.Entity;

import java.util.HashMap;


/**
 * Class to store numerical range style questions.
 */
@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
public class NumRange extends Question {

    /**
     * Setting a minimum and maximum range for choices.
     */
    private int minRange;
    private int maxRange;

    /**
     * Hashmap to take the question, and a range of integers from 1-10 for the selections.
     */
    private HashMap<String, Integer> answers;

    public NumRange(){
        super();
        this.answers = new HashMap<>();
    }

    public NumRange(String question, int minRange, int maxRange){
        super(question);
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.answers = new HashMap<>();
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public HashMap<String, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<String, Integer> answers) {
        this.answers = answers;
    }

    public void addAnswers(HashMap<String, Integer> answers){
        this.answers.putAll(answers);
    }
}
