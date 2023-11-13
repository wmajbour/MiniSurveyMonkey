import org.MiniSurveyMonkey.Controller.WebController;
import org.MiniSurveyMonkey.Model.MultipleChoice;
import org.junit.jupiter.api.Test;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@WebMvcTest(WebController.class)
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyRepository repository;

    @Test
    public void testGetAllSurveys() throws Exception {
        // Arrange
        Survey survey = new Survey();
        survey.setId(1);
        survey.setName("Sample Survey");

        // Stubbing
        repository.save(survey);
        MultipleChoice mcq1 = new MultipleChoice("Testing question string",
                new ArrayList<>(Arrays.asList("choice1", "choice2", "choice3")));
        repository.save(new Survey("Another survey", new ArrayList<>(Arrays.asList(mcq1))));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/surveys"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("surveyList"))
                .andExpect(MockMvcResultMatchers.model().attribute("surveys", Collections.singletonList(survey)));
    }

    @Test
    public void testShowAddSurveyForm() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/surveys/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("addSurvey"));
    }

    @Test
    public void testAddSurvey() throws Exception {
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/surveys/add")
                        .param("name", "New Survey"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/surveys"));
        Iterable<Survey> savedSurveys = repository.findAll();
    }
}
