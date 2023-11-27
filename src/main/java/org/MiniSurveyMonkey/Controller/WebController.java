package org.MiniSurveyMonkey.Controller;

import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.MiniSurveyMonkey.Model.NumRange;
import org.MiniSurveyMonkey.Model.OpenEnded;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.MultipleChoiceRepository;
import org.MiniSurveyMonkey.Repo.NumRangeRepository;
import org.MiniSurveyMonkey.Repo.OpenEndedRepository;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.MiniSurveyMonkey.Model.Choice;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

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

    @GetMapping("/user")
    public String openSurveys() {
        return "user";
    }

    @GetMapping("/testsurveyview")
    public String testSurveyView(Model model) {
        Survey survey = repository.findById(1);
        model.addAttribute(survey);
        return "SurveyView";
    }

    @GetMapping("/testsurveypreviewview")
    public String testSurveyPreviewView(Model model) {
        Survey survey = repository.findById(1);
        model.addAttribute(survey);
        return "SurveyPreviewView";
    }

    @GetMapping("/surveyor")
    public String showSurveyor() {
        return "Surveyor";
    }

    @GetMapping("/surveyor/PrintSurveys")
    public String showCurrentSurveys(Model model) {
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : repository.findAll()) {
            surveys.add(survey);
        }
        model.addAttribute("surveys", surveys);
        return "PrintSurveys";
    }

    @GetMapping("/surveyor/SurveyCreator")
    public String showAddSurveyForm() {
        return "SurveyCreator";
    }

    @PostMapping("/surveyor/SurveyCreator/addmcq")
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
        Survey survey = repository.findById(surveyId).orElse(null);
        survey.addQuestion(mcq);
        repository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @Transactional
    @PostMapping("/surveyor/SurveyCreator/addnrq")
    public String addNrq(Model model, @RequestParam("nrq") String question,
            @RequestParam("min") int min,
            @RequestParam("max") int max,
            @RequestParam("surveyId") Integer surveyId) {

        NumRange nrq = new NumRange(question, min, max);
        Survey survey = repository.findById(surveyId).orElse(null);
        survey.addQuestion(nrq);
        repository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @PostMapping("/surveyor/SurveyCreator/addoeq")
    public String addOeq(Model model, @RequestParam("oeq") String question,
            @RequestParam("surveyId") Integer surveyId) {
        OpenEnded oeq = new OpenEnded(question);
        Survey survey = repository.findById(surveyId).orElse(null);
        survey.addQuestion(oeq);
        repository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @PostMapping("/surveyor/SurveyCreator/addSurvey")
    public String createSurvey(Model model, @RequestParam("surveyName") String name) {
        Survey survey = new Survey(name);
        repository.save(survey);
        model.addAttribute(survey);
        return "SurveyAddQuestions";
    }

    @DeleteMapping("/survey/{surveyId}/close")
    public void deleteSurvey(@PathVariable(value = "surveyId") int surveyId) {
        repository.deleteById(surveyId);
    }

    @PostMapping("/surveyor/{surveyId}/close")
    public Survey closeAndSaveSurvey(@PathVariable(value = "surveyId") int surveyId) {
        Survey survey = repository.findById(surveyId);
        survey.close();
        repository.save(survey);
        return survey;
    }


    /*
    @PostMapping("/surveyor/{surveyId}/mcq")
    public MultipleChoice addMcq(@PathVariable(value = "surveyId") int surveyId, @RequestBody MultipleChoice question){
        Survey survey = repository.findById(surveyId);
        mcqRepository.save(question);
        survey.addQuestion(question);
        repository.save(survey);
        return question;
    }

    @PostMapping("/surveyor/{surveyId}/nrq")
    public NumRange addNrq(@PathVariable(value = "surveyId") int surveyId, @RequestBody NumRange nrq){
        Survey survey = repository.findById(surveyId);
        nrqRepository.save(nrq);
        survey.addQuestion(nrq);
        repository.save(survey);
        return nrq;
    }

    @PostMapping("/surveyor/{surveyId}/oeq")
    public OpenEnded addOeq(@PathVariable(value = "surveyId") int surveyId, @RequestBody OpenEnded oeq){
        Survey survey = repository.findById(surveyId);
        oeqRepository.save(oeq);
        survey.addQuestion(oeq);
        repository.save(survey);
        return oeq;
    }
     */
}
