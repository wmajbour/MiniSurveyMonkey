package org.MiniSurveyMonkey.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 * Class to store open-ended style questions.
 */
@Entity
public class OpenEnded extends Question {

    private String answers;
    @ManyToOne
    private Survey survey;

    /**
     * Constructor for open-ended.
     */
    public OpenEnded() {
        super();
        this.answers = new String();
    }

    public OpenEnded(String question){
        super(question, "open-ended");
        this.answers = new String();
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

}
