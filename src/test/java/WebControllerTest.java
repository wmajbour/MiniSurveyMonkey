
import org.MiniSurveyMonkey.Controller.WebController;
import org.MiniSurveyMonkey.Model.Choice;
import org.MiniSurveyMonkey.Model.MultipleChoice;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.*;
import static org.mockito.Mockito.verify;


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
    public void testGetAllSurveys() {
        // Arrange
        Survey survey = new Survey();
        survey.setId(1);
        survey.setName("Sample Survey");

        var set1 = new HashSet<Choice>();
        var set2 = new HashSet<Choice>();
        set1.add(new Choice("Year 1", false));
        set1.add(new Choice("Year 2", false));
        set1.add(new Choice("Year 3", false));
        set1.add(new Choice("Year 4", false));
        set2.add(new Choice("Spring", false));
        set2.add(new Choice("Summer", false));
        set2.add(new Choice("Fall", false));
        set2.add(new Choice("Winter", false));

        // Stubbing
        MultipleChoice mcq1 = new MultipleChoice("Which was your favourite year of University?",
                set1);
        MultipleChoice mcq2 = new MultipleChoice("What season of the year is your favourite?",
                set2);
        repository.save(survey);

        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.get("/surveyor/PrintSurveys"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("PrintSurveys"))
                    .andExpect(MockMvcResultMatchers.model().attribute("surveys", Collections.singletonList(survey)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testShowAddSurveyForm() {
        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.get("/surveys/add"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("addSurvey"));
        } catch (Exception e) {

        }

    }

    @Test
    public void testAddSurvey() {
        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.post("/surveys/SurveyCreator/addSurvey")
                    .param("name", "New Survey"))
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("/surveys"));
        } catch (Exception e) {

        }

        // Verify that repository.save was called with the expected arguments
        verify(repository).save(new Survey("New Survey"));
    }

    @Test
    public void testSurveyorPreview() {
        // Arrange
        Survey survey = new Survey();
        survey.setId(1);
        survey.setName("Sample Survey");
        repository.save(survey);

        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.get("/surveyor/PrintSurveys/surveyorPreview"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("SurveyPreviewView"))
                    .andExpect(MockMvcResultMatchers.model().attribute("survey", survey));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAnswerSurvey() {
        // Arrange
        int surveyId = 1;
        Survey survey = new Survey();
        survey.setId(surveyId);
        survey.setName("Sample Survey");
        repository.save(survey);

        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.get("/user/surveyView/{surveyId}", surveyId))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("SurveyView"))
                    .andExpect(MockMvcResultMatchers.model().attribute("survey", survey));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCloseAndSaveSurvey() {
        // Arrange
        int surveyId = 1;
        Survey survey = new Survey();
        survey.setId(surveyId);
        survey.setName("Sample Survey");
        repository.save(survey);

        try {
            // Act & Assert
            mockMvc.perform(MockMvcRequestBuilders.post("/surveyor/{surveyId}/close", surveyId))
                    .andExpect(MockMvcResultMatchers.status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<Survey> closedSurvey = Optional.ofNullable(repository.findById(surveyId));

    }
}
