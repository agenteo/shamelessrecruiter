package shamelessrecruiter.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateRecruiterControllerTest {

    // I couldn't find a way to mock the @Valid response used in the controller
    // to validate the ReportRecruiterForm
    @Test
    public void testRecruiterCreation() throws Exception{
        RecruiterRepository mockRepository = mock(RecruiterRepository.class);
        ReportRecruiterController controller= new ReportRecruiterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/create")
                .param("name", "Ajeje Brazu")
                .param("email", "ajeje@bra.zu")
                .param("message", "Dear candidate...")).
                andExpect(view().name("recruiterReportCreated"));
    }
}
