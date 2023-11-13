import org.MiniSurveyMonkey.Controller.WebController;
import org.MiniSurveyMonkey.Model.Survey;
import org.MiniSurveyMonkey.Repo.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class TestWebApp {

    @Value(value = "${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WebController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SurveyRepository repository;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }
    @Test
    public void getSurvey() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost: " + port + "/", String.class)).contains("[]");
    }

}
