/*package org.MiniSurveyMonkey.Controller;

import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WebController {
    @Autowired
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
        Optional<Survey> surveyOptional = repository.findById(id);

        // Check if the survey exists
        return surveyOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
*/
package org.MiniSurveyMonkey.Controller;

import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.MultipleChoiceRepository;
import org.MiniSurveyMonkey.Repo.NumRangeRepository;
import org.MiniSurveyMonkey.Repo.OpenEndedRepository;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    private final SurveyRepository repository;
    private final MultipleChoiceRepository mcqRepository;
    private final NumRangeRepository nrqRepository;
    private final OpenEndedRepository oeqRepository;

    @Autowired
    public WebController(SurveyRepository surveyRepository, MultipleChoiceRepository mcqRepository, NumRangeRepository nrqRepository, OpenEndedRepository oeqRepository) {
        this.repository = surveyRepository;
        this.mcqRepository = mcqRepository;
        this.nrqRepository = nrqRepository;
        this.oeqRepository = oeqRepository;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : repository.findAll()){
            surveys.add(survey);
        }
        return surveys;
    }

    @GetMapping("/surveyor/SurveyCreator")
    public String showAddSurveyForm() {
        return "SurveyCreator";
    }

    @GetMapping("/surveyor")
    public String showSurveyor() {
        return "Surveyor";
    }

    @PostMapping("/surveyor/survey")
    public Survey createSurvey(@RequestBody Survey survey){repository.save(survey); return survey;}

    @DeleteMapping("/survey/{surveyId}")
    public void deleteSurvey(@PathVariable(value="surveyId") int surveyId){repository.deleteById(surveyId);}

    @PostMapping("/surveyor/{surveyId}/close")
    public Survey closeAndSaveSurvey(@PathVariable(value = "surveyId") int surveyId){
        Survey survey = repository.findById(surveyId);
        survey.close();
        repository.save(survey);
        return survey;
    }



}
