package org.MiniSurveyMonkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.tiles3.SpringWildcardServletTilesApplicationContext;


@SpringBootApplication
public class MiniSurveyMonkey {

    private static final Logger log = LoggerFactory.getLogger(MiniSurveyMonkey.class);

    public static void main(String[] args) {
        SpringApplication.run(MiniSurveyMonkey.class, args);
    }

    
}
