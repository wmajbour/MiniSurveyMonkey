package org.MiniSurveyMonkey.Model;

import jakarta.persistence.*;

import java.util.List;


/**
 * Class to store numerical range style questions.
 */

@Entity
@Table(name = "num_range")
public class NumRange extends Question {

    /**
     * Setting a minimum and maximum range for choices.
     */
    @Column(name = "min_range")
    private int minRange;
    @Column(name = "max_range")
    private int maxRange;

    /**
     * number chosen by user. value is obtained using the number input HTML
     * element.
     * Set as 0 by default.
     */
    @Column(name = "value_column")
    private int value;


    @ElementCollection(targetClass = Float.class)
    private List<Float> answers;

    @ManyToOne
    private Survey survey;

    public NumRange(){
        super();
        value = 0;
    }

    public NumRange(String question, int minRange, int maxRange){
        super(question, "num-range");
        this.minRange = minRange;
        this.maxRange = maxRange;
        value = 0;

    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addAnswer(Float answer){
        this.answers.add(answer);
    }

}
