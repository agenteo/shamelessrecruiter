package shamelessrecruiter.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateRecruiterControllerTest {

    @Test
    public void testRecruiterCreation() throws Exception{
        RecruiterRepository mockRepository = mock(RecruiterRepository.class);
        CreateRecruiterController controller= new CreateRecruiterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/create").param("email", "enrico@example.com")).
                andExpect(view().name("recruiterCreated"));
    }
}
