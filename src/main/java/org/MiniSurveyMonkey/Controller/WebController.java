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
        import org.MiniSurveyMonkey.Repo.SurveyRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

        import java.util.List;

@Controller
@RequestMapping("/surveys")
public class WebController {

    private final SurveyRepository repository;

    @Autowired
    public WebController(SurveyRepository surveyRepository) {
        this.repository = surveyRepository;
    }

    @GetMapping
    public String getAllSurveys(Model model) {
        List<Survey> surveys = (List<Survey>) repository.findAll();
        model.addAttribute("surveys", surveys);
        return "surveyList"; // Assuming you have an HTML template named "surveyList.html"
    }

    @GetMapping("/add")
    public String showAddSurveyForm() {
        return "addSurvey"; // Assuming you have an HTML template named "addSurvey.html"
    }

    @PostMapping("/add")
    public String addSurvey(Survey survey) {
        repository.save(survey);
        return "redirect:/surveys";
    }
}
