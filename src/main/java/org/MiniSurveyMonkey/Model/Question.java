package org.MiniSurveyMonkey.Model;

/**
 * This class will represent the general template for the survey questions.
 */
public abstract class Question {
    private Integer id;
    private String question;

    public Question() {

    }

    public Question(String question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
