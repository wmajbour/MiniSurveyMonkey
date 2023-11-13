package org.MiniSurveyMonkey;

import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.tiles3.SpringWildcardServletTilesApplicationContext;

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
                    new ArrayList<String>(Arrays.asList("choice1", "choice2", "choice3")));

        };
    }

}
