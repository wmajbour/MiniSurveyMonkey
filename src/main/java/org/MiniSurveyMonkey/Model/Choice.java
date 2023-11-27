package org.MiniSurveyMonkey.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author mohammad
 */
@Entity
public class Choice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String choice;
    
    private Boolean selected;
    
    public Choice() {
        choice = "";
        selected = false;
    }
    
    
    public Choice(String choice, Boolean valueChosen) {
        this.choice = choice;
        this.selected = valueChosen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
    
    
}
