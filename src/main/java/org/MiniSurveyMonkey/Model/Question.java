package org.MiniSurveyMonkey.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String question;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Question() {}


    public Question(String question, String type) {
        this.question = question;
        this.type = type;
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

    @JsonCreator
    public static Question createQuestion(
            @JsonProperty("question") String question,
            @JsonProperty("type") String type,
            @JsonProperty("lowerBound") Integer lowerBound,
            @JsonProperty("upperBound") Integer upperBound) {

        if ("MCQ".equals(type)) {
            return new MultipleChoice();
        } else if ("NumQ".equals(type)) {
            return new NumRange(question, lowerBound, upperBound);
        } else if ("OpenQ".equals(type)) {
            return new OpenEnded(question);
        } else {
            throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }

}
