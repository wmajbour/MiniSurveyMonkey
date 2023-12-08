package org.MiniSurveyMonkey.Controller;

import java.util.HashSet;
import java.util.Set;
import org.MiniSurveyMonkey.Model.Choice;
import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.MiniSurveyMonkey.Model.NumRange;
import org.MiniSurveyMonkey.Model.OpenEnded;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mohammad
 */
@Controller
@RequestMapping(value = "/surveyor/SurveyCreator")
public class SurveyCreatorController {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyCreatorController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @PostMapping("/addSurvey")
    public String createSurvey(Model model, @RequestParam("surveyName") String name) {
        Survey survey = new Survey(name);
        surveyRepository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @PostMapping("/addmcq")
    public String addMcq(Model model, @RequestParam("mcq") String question,
            @RequestParam("mcq1") String choice1,
            @RequestParam("mcq2") String choice2,
            @RequestParam("mcq3") String choice3,
            @RequestParam("mcq4") String choice4,
            @RequestParam("surveyId") Integer surveyId) {
        Set<Choice> choices = new HashSet<>();
        MultipleChoice mcq = new MultipleChoice(question, choices);
        choices.add(new Choice(choice1, false));
        choices.add(new Choice(choice2, false));
        choices.add(new Choice(choice3, false));
        choices.add(new Choice(choice4, false));
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        survey.addQuestion(mcq);
        surveyRepository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @Transactional
    @PostMapping("/addnrq")
    public String addNrq(Model model, @RequestParam("nrq") String question,
            @RequestParam("min") int min,
            @RequestParam("max") int max,
            @RequestParam("surveyId") Integer surveyId) {

        NumRange nrq = new NumRange(question, min, max);
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        survey.addQuestion(nrq);
        surveyRepository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @PostMapping("/addoeq")
    public String addOeq(Model model, @RequestParam("oeq") String question,
            @RequestParam("surveyId") Integer surveyId) {
        OpenEnded oeq = new OpenEnded(question);
        Survey survey = surveyRepository.findById(surveyId).orElse(null);
        survey.addQuestion(oeq);
        surveyRepository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

}
