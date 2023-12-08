
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.MiniSurveyMonkey.Controller.WebController;
import org.MiniSurveyMonkey.Repo.MultipleChoiceRepository;
import org.MiniSurveyMonkey.Repo.NumRangeRepository;
import org.MiniSurveyMonkey.Repo.OpenEndedRepository;
import org.junit.jupiter.api.Test;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebController.class)
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyRepository repository;

    @MockBean
    private MultipleChoiceRepository mcqRepo;

    @MockBean
    private NumRangeRepository nrqRepo;

    @MockBean
    private OpenEndedRepository oeqRepo;

    @Test
    public void testShowHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }


    @Test
    public void testShowSurveysForUser() throws Exception {
        // Setup mock behavior for this test
        List<Survey> mockSurveys = new ArrayList<>();
        mockSurveys.add(new Survey());
        when(repository.findAll()).thenReturn(mockSurveys);

        mockMvc.perform(get("/user/showSurveys"))
                .andExpect(status().isOk())
                .andExpect(view().name("PrintSurveysForUser"))
                .andExpect(model().attributeExists("surveys"));
    }

    @Test
    public void testGetAllSurveys() throws Exception {
        // Prepare mock data
        List<Survey> mockSurveys = new ArrayList<>();
        mockSurveys.add(new Survey());
        mockSurveys.add(new Survey());
        when(repository.findAll()).thenReturn(mockSurveys);

        // Perform the request and assert results
        mockMvc.perform(get("/user/showSurveys"))
                .andExpect(status().isOk())
                .andExpect(view().name("PrintSurveysForUser"))
                .andExpect(model().attribute("surveys", hasSize(2)))
                .andExpect(model().attribute("surveys", is(mockSurveys)));
    }





    @Test
    public void testDeleteSurvey() throws Exception {
        int surveyId = 1;
        mockMvc.perform(delete("/survey/" + surveyId + "/close"))
                .andExpect(status().isOk());

        verify(repository, times(1)).deleteById(surveyId);
    }









    @Test
    public void testCloseAndSaveSurvey() throws Exception {
        int surveyId = 1;
        Survey survey = new Survey("Sample Survey");
        survey.setId(surveyId); // Set an ID for the survey

        // Mock the repository to return the survey when findById is called
        when(repository.findById(surveyId)).thenReturn(survey);

        mockMvc.perform(post("/surveyor/" + surveyId + "/close"))
                .andExpect(status().isOk())
                .andExpect(content().string("SurveyResults"));

        verify(repository, times(1)).save(survey);
        assertFalse(survey.isOpen());
    }
    @Test
    public void testSurveyorPreview() throws Exception {
        int surveyId = 1;
        Survey survey = new Survey("Sample Survey");
        when(repository.findById(surveyId)).thenReturn(survey);

        mockMvc.perform(get("/surveyor/PrintSurveys/surveyorPreview"))
                .andExpect(status().isOk())
                .andExpect(view().name("SurveyPreviewView"))
                .andExpect(model().attribute("survey", survey));
    }
    @Test
    public void testAddSurvey() throws Exception {
        String surveyName = "New Survey";

        mockMvc.perform(post("/surveyor/SurveyCreator/addSurvey")
                        .param("surveyName", surveyName))
                .andExpect(status().isOk())
                .andExpect(view().name("SurveyAddQuestions"));


    }





}

