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
            Survey survey = new Survey();
            repository.save(survey);
            /*
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
            MultipleChoice mcq2 = new MultipleChoice("Testing question2 string",
                    set2);
            mcqRepo.save(mcq1);
            mcqRepo.save(mcq2);
            OpenEnded oeq1 = new OpenEnded("Testing Question String");
            oeRepo.save(oeq1);
            NumRange nrq1 = new NumRange("Question", 1, 10);
            nmRepo.save(nrq1);
            Survey surveyMC = new Survey("Survey for Multiple Choice Questions", new
                    ArrayList<>(Arrays.asList(mcq1, mcq2)));
            repository.save(surveyMC);

            Survey surveyOE = new Survey("Survey for Open Ended Questions",
                    new ArrayList<>(List.of(oeq1)));
            repository.save(surveyOE);

            Survey surveyNR = new Survey("Survey for Numerical Range Questions",
                    new ArrayList<>(List.of(nrq1)));
            repository.save((surveyNR));

            for (Survey survey : repository.findAll()) {
                log.info("Survey ID: {}", survey.getId());
                log.info("Survey Name: {}", survey.getName());
                log.info(""); // Add a separator between surveys
            }*/
        };
    }
}
