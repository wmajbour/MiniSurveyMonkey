package org.MiniSurveyMonkey;

import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.MiniSurveyMonkey.Model.NumRange;
import org.MiniSurveyMonkey.Model.OpenEnded;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication(scanBasePackages = "org.MiniSurveyMonkey")

public class MiniSurveyMonkey {

    private static final Logger log = LoggerFactory.getLogger(MiniSurveyMonkey.class);

    public static void main(String[] args) {
        SpringApplication.run(MiniSurveyMonkey.class, args);
    }

    @Bean
    public CommandLineRunner demo(SurveyRepository repository) {
        return (args) -> {

            MultipleChoice mcq1 = new MultipleChoice("Testing question1 string",
                    new ArrayList<>(Arrays.asList("choice1", "choice2", "choice3")));
            MultipleChoice mcq2 = new MultipleChoice("Testing question2 string",
                    new ArrayList<>(Arrays.asList("choice1.2", "choice2.2", "choice3.2")));

            OpenEnded oeq1 = new OpenEnded("Testing Question String");

            NumRange nrq1 = new NumRange("Question", 1, 10);

            Survey surveyMC = new Survey("Survey for Multiple Choice Questions", new
                    ArrayList<>(Arrays.asList(mcq1, mcq2)));
            repository.save((surveyMC));

            Survey surveyOE = new Survey("Survey for Open Ended Questions",
                    new ArrayList<>(List.of(oeq1)));
            repository.save((surveyOE));

            Survey surveyNR = new Survey("Survey for Numerical Range Questions",
                    new ArrayList<>(List.of(nrq1)));
            repository.save((surveyNR));

            for (Survey survey : repository.findAll()) {
                log.info("Survey ID: {}", survey.getId());
                log.info("Survey Name: {}", survey.getName());
                log.info(""); // Add a separator between surveys
            }
        };
    }
}
