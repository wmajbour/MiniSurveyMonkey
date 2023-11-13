package org.MiniSurveyMonkey.Model;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class WebController {
    private SurveyRepository repository;

    // Constructor Injection
    public WebController(SurveyRepository repository){
        this.repository = repository;
    }

    // Create Survey Endpoint
    @PostMapping("/CreateSurvey")
    public Survey createSurvey(@RequestBody Survey survey){
        // Save the new survey to the repository
        repository.save(survey);
        return survey;
    }
    @GetMapping("/Survey/{id}")
    public ResponseEntity<Survey> getSurvey(@PathVariable Integer id) {
        // Retrieve survey by ID from the repository
        Optional<Survey> surveyOptional = Optional.ofNullable(repository.findById(id));

        // Check if the survey exists
        return surveyOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
