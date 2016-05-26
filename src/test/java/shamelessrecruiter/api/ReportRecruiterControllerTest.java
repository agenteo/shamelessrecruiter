package shamelessrecruiter.api;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import shamelessrecruiter.web.RecruiterRepository;

import java.nio.charset.Charset;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ReportRecruiterControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    // NOTE: This controller test boundary starts colliding with integration test.
    // The @Valid used in the controller doesn't seem to be easily mockable
    @Test
    public void testRecruiterCreation() throws Exception{
        RecruiterRepository mockRepository = mock(RecruiterRepository.class);
        ReportRecruiterApiController controller= new ReportRecruiterApiController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/api/reportRecruiter")
                .contentType(contentType)
                .content("{\"name\": \"Ajeje Brazu\", \"email\": \"ajeje@bra.zu\", \"message\": \"Dear candidate...\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.response").value("good service"));
    }

}