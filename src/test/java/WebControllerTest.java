import org.MiniSurveyMonkey.Controller.WebController;
import org.MiniSurveyMonkey.Model.Choice;
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
import java.util.HashSet;

import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebController.class)
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

        var set1 = new HashSet<Choice>();
        var set2 = new HashSet<Choice>();
        set1.add(new Choice("Year 1",false));
        set1.add(new Choice("Year 2",false));
        set1.add(new Choice("Year 3",false));
        set1.add(new Choice("Year 4",false));
        set2.add(new Choice("Spring",false));
        set2.add(new Choice("Summer",false));
        set2.add(new Choice("Fall",false));
        set2.add(new Choice("Winter",false));

        // Stubbing
        MultipleChoice mcq1 = new MultipleChoice("Which was your favourite year of University?",
                set1);
        MultipleChoice mcq2 = new MultipleChoice("What season of the year is your favourite?",
                set2);
        repository.save(survey);

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

        // Verify that repository.save was called with the expected arguments
        verify(repository).save(new Survey("New Survey"));
    }

    
}
