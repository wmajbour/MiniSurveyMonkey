package org.MiniSurveyMonkey.Model;

import jakarta.persistence.*;



import java.util.HashSet;
import java.util.Set;


/**
 * Class to store multiple choice style questions.
 */
@Entity
public class MultipleChoice extends Question{

    /**
     * Set that takes a string for the choice and a Integer to check if it is checked.
     * A minimum of one choice MUST be selected.
     * If none apply, have separate option; ex. "None of the above".
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "multiple_choice_question_id")
    private Set<Choice> choices;

    @ManyToOne
    private Survey survey;

    /**
     * Constructor for multiple choice.
     */
    public MultipleChoice(){
        super();
        this.choices = new HashSet<>();
    }

    public Set<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Set<Choice> choices) {
        this.choices = choices;
    }

    public MultipleChoice(String question, Set<Choice> choices){
        super(question, "multiple-choice");
        this.choices = choices;
    }

    public void addChoice(Choice choice){
        choices.add(choice);
    }


}
