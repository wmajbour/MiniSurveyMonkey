package org.MiniSurveyMonkey.Model;

import java.awt.image.BandedSampleModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to store numerical range style questions.
 */
public class NumRange extends Question {

    /**
     * Setting a minimum and maximum range for choices.
     */
    private int minRange;
    private int maxRange;
    private String questionText;
    /**
     * Hashmap to take the question, and a range of integers from 1-10 for the selections.
     */
    private HashMap<String, Integer> answers;

    public NumRange(){
        super();
        this.answers = new HashMap<>();
    }

}
