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
import java.util.List;
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

    @GetMapping("/user/surveyView")
    public String surveyViewForUser(Model model){
        Survey survey = repository.findById(1);
        model.addAttribute(survey);
        return "SurveyView";
    }

    @GetMapping("/user/showSurveys")
    public String showSurveysForUser(Model model){
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : repository.findAll()){
            surveys.add(survey);
        }
        model.addAttribute("surveys", surveys);
        return "PrintSurveysForUser";
    }

    @GetMapping("/surveyor/PrintSurveys/surveyorPreview")
    public String surveyorPreview(Model model){
        Survey survey = repository.findById(1);
        model.addAttribute(survey);
        return "SurveyPreviewView";
    }

    @GetMapping("/surveyor/SurveyCreator")
    public String showAddSurveyForm() {
        return "SurveyCreator";
    }

    @GetMapping("/surveyor/PrintSurveys")
    public String showCurrentSurveys(Model model){
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : repository.findAll()){
            surveys.add(survey);
        }
        model.addAttribute("surveys", surveys);
        return "PrintSurveys";
    }

    @GetMapping("/surveyor")
    public String showSurveyor() {
        return "Surveyor";
    }

    @PostMapping("/surveyor/survey")
    public Survey createSurvey(@RequestBody Survey survey){
        repository.save(survey);
        return survey;
    }

    @DeleteMapping("/survey/{surveyId}/close")
    public void deleteSurvey(@PathVariable(value="surveyId") int surveyId){repository.deleteById(surveyId);}


    @PostMapping("/surveyor/{surveyId}/close")
    public Survey closeAndSaveSurvey(@PathVariable(value = "surveyId") int surveyId){
        Survey survey = repository.findById(surveyId);
        survey.close();
        repository.save(survey);
        return survey;
    }

    @PostMapping("/surveyor/{surveyId}/mcq")
    public MultipleChoice addMcq(@PathVariable(value = "surveyId") int surveyId, @RequestBody MultipleChoice mcq){
        Survey survey = repository.findById(surveyId);
        mcqRepository.save(mcq);
        survey.addQuestion(mcq);
        repository.save(survey);
        return mcq;
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


}
