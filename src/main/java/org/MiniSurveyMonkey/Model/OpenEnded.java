package org.MiniSurveyMonkey.Model;

/**
 * Class to store open-ended style questions.
 */

public class OpenEnded extends Question {

    private String answers;


    /**
     * Constructor for open-ended.
     */
    public OpenEnded() {
        super();
        this.answers = new String();
    }

    public OpenEnded(String question){
        super(question);
        this.answers = new String();
    }

}
