package org.MiniSurveyMonkey;

import org.MiniSurveyMonkey.Model.MultipleChoice;
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



@SpringBootApplication
public class MiniSurveyMonkey {

    private static final Logger log = LoggerFactory.getLogger(MiniSurveyMonkey.class);

    public static void main(String[] args) {
        SpringApplication.run(MiniSurveyMonkey.class, args);
    }

    @Bean
    public CommandLineRunner demo(SurveyRepository repository){
        return (args) -> {

            MultipleChoice mcq1 = new MultipleChoice("Testing question string",
                    new ArrayList<>(Arrays.asList("choice1", "choice2", "choice3")));
            Survey survey1 = new Survey("Survey 1", new ArrayList<>(Arrays.asList(mcq1)));
            repository.save((survey1));

            log.info("Surveys");
            log.info("-----------");
            for(Survey survey: repository.findAll()){
                log.info(survey.getId().toString());
            }
            log.info("");
        };
    }

}