package org.MiniSurveyMonkey.Model;

import java.util.HashMap;


/**
 * Class to store numerical range style questions.
 */
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

    /**
     * Adds an answer to the answers HashMap if it is within the specified range.
     *
     * @param key    The unique key for the answer (e.g., user ID).
     * @param answer The answer to be added.
     * @return true if the answer is within range, false otherwise.
     */
    public boolean addAnswer(String key, int answer) {
        if (answer >= minRange && answer <= maxRange) {
            answers.put(key, answer);
            return true;
        }
        return false;
    }
}
