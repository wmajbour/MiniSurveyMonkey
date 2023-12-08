package org.MiniSurveyMonkey.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mohammad
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final SurveyRepository surveyRepository;

    @Autowired
    public UserController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/showSurveys")
    public String showSurveysForUser(Model model) {
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : surveyRepository.findAll()) {
            surveys.add(survey);
        }
        model.addAttribute("surveys", surveys);
        return "PrintSurveysForUser";
    }

    @GetMapping("/surveyView")
    public String answerSurvey(Model model, @RequestParam("surveyId") String surveyId) {
        Survey survey = surveyRepository.findById(Integer.parseInt(surveyId));
        model.addAttribute(survey);
        return "SurveyViewForUser";

    }
    
    @PostMapping("/submitSurvey")
    public String submitSurvey(Model model, Map<String, String> formData) {
        
        return showSurveysForUser(model);
    }

}
