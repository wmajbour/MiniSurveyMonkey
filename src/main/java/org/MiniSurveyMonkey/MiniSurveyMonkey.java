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
import java.util.HashSet;
import java.util.List;
import org.MiniSurveyMonkey.Model.Choice;
import org.MiniSurveyMonkey.Repo.MultipleChoiceRepository;
import org.MiniSurveyMonkey.Repo.NumRangeRepository;
import org.MiniSurveyMonkey.Repo.OpenEndedRepository;


@SpringBootApplication
public class MiniSurveyMonkey {

    private static final Logger log = LoggerFactory.getLogger(MiniSurveyMonkey.class);

    public static void main(String[] args) {
        SpringApplication.run(MiniSurveyMonkey.class, args);
    }

    @Bean
    public CommandLineRunner demo(
            SurveyRepository repository,
            MultipleChoiceRepository mcqRepo,
            NumRangeRepository nmRepo,
            OpenEndedRepository oeRepo) {
        return (args) -> {
            Survey survey1 = new Survey();
            repository.save(survey1);

            Survey survey2 = new Survey();
            repository.save(survey2);

            var set1 = new HashSet<Choice>();
            var set2 = new HashSet<Choice>();
            set1.add(new Choice("choice1",false));
            set1.add(new Choice("choice2",false));
            set1.add(new Choice("choice3",false));
            set2.add(new Choice("choice4",false));
            set2.add(new Choice("choice5",false));
            set2.add(new Choice("choice6",false));
            MultipleChoice mcq1 = new MultipleChoice("Testing question1 string",
            set1);

            MultipleChoice mcq2 = new MultipleChoice("Testing question2 string", set2);

            /*log.info("Surveys Found:");
            log.info("---------------");
            for(Survey survey : repository.findAll()){
                log.info(survey.getId().toString());
            }*/
        };
    }
}
