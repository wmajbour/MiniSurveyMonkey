package org.MiniSurveyMonkey.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a survey with a unique identifier, name, and a list of questions.
 * The survey can be created with a name and an initial list of questions.
 * Questions can be added to the survey using the setQuestions method.
 * The survey can be opened or closed using the isOpen and close methods.
 */
@Entity
public class Survey {

    /**
     * The unique identifier for the survey.
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    /**
     * The name of the survey.
     */
    private String name;

    /**
     * The list of questions associated with the survey.
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    /**
     * A flag indicating whether the survey is open or closed.
     */
    private boolean open;

    /**
     * Constructs an empty survey with an empty list of questions and sets it to open.
     */
    public Survey() {
        this.questions = new ArrayList<>();
        this.open = true;
    }

    /**
     * Constructs a survey with a specified name, an empty list of questions, and sets it to open.
     *
     * @param name The name of the survey.
     */
    public Survey(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
        this.open = true;
    }

    /**
     * Constructs a survey with a specified name and list of questions, and sets it to open.
     *
     * @param name      The name of the survey.
     * @param questions The list of questions associated with the survey.
     */
    public Survey(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
        this.open = true;
    }

    /**
     * Sets the name of the survey.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the survey.
     *
     * @return The name of the survey.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the unique identifier of the survey.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the survey.
     *
     * @return The unique identifier of the survey.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the list of questions associated with the survey.
     *
     * @param questions The list of questions to set.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    /**
     * Gets the list of questions associated with the survey.
     *
     * @return The list of questions associated with the survey.
     */
    public List<Question> getQuestions() {
        return this.questions;
    }

    /**
     * Checks if the survey is open.
     *
     * @return True if the survey is open, false otherwise.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Closes the survey.
     */
    public void close() {
        this.open = false;
    }
}
