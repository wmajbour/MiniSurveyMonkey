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
import java.util.HashSet;
import org.MiniSurveyMonkey.Model.Choice;
import org.MiniSurveyMonkey.Model.Question;
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

            var set1 = new HashSet<Choice>();
            var set2 = new HashSet<Choice>();
            set1.add(new Choice("Year 1",false));
            set1.add(new Choice("Year 2",false));
            set1.add(new Choice("Year 3",false));
            set1.add(new Choice("Year 4",false));
            set2.add(new Choice("Spring",false));
            set2.add(new Choice("Summer",false));
            set2.add(new Choice("Fall",false));
            set2.add(new Choice("Winter",false));
            MultipleChoice mcq1 = new MultipleChoice("Which was your favourite year of University?",
                    set1);
            MultipleChoice mcq2 = new MultipleChoice("What season of the year is your favourite?",
                    set2);
            OpenEnded oeq1 = new OpenEnded("Do you like or dislike school? Explain.");
            NumRange nrq1 = new NumRange("Your age", 13, 100);
            var qs = new ArrayList<Question>();
            qs.add(mcq1);
            qs.add(mcq2);
            qs.add(oeq1);
            qs.add(nrq1);
            Survey survey = new Survey("School and Seasons Survey", qs);
            repository.save(survey);

        };
    }
}
