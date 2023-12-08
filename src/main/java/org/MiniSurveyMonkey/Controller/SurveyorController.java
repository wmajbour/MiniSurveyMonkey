package org.MiniSurveyMonkey.Controller;

import java.util.ArrayList;
import java.util.List;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mohammad
 */
@Controller
@RequestMapping(value = "/surveyor")
public class SurveyorController {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyorController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/SurveyCreator")
    public String showAddSurveyForm() {
        return "SurveyCreator";
    }

    @GetMapping("/PrintSurveys")
    public String showCurrentSurveys(Model model) {
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : surveyRepository.findAll()) {
            surveys.add(survey);
        }
        model.addAttribute("surveys", surveys);
        return "PrintSurveysForSurveyor";
    }

    @GetMapping("/PrintSurveys/surveyorPreview")
    public String surveyorPreview(Model model, @RequestParam("surveyId") String surveyId) {
        Survey survey = surveyRepository.findById(Integer.parseInt(surveyId));
        model.addAttribute(survey);
        return "SurveyPreviewForSurveyor";
    }

    @DeleteMapping("/survey/{surveyId}/close")
    public void deleteSurvey(@PathVariable(value = "surveyId") int surveyId) {
        surveyRepository.deleteById(surveyId);
    }

    @PostMapping("/close")
    public String closeAndSaveSurvey(@RequestParam(value = "surveyId") String surveyId, Model model) {
        Survey survey = surveyRepository.findById(Integer.parseInt(surveyId));
        survey.close();
        surveyRepository.save(survey);
        return showCurrentSurveys(model);
    }

    @GetMapping("/results")
    public String viewResults(@RequestParam(value = "surveyId") String surveyId, Model model){
        Survey survey = surveyRepository.findById(Integer.parseInt(surveyId));
        model.addAttribute("survey", survey);
        return "SurveyResutls";
    }
}
