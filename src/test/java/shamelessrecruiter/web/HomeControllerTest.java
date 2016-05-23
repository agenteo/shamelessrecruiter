package shamelessrecruiter.web;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class HomeControllerTest {
    @InjectMocks
    private RecruiterRepository recruiterRepository;

    @Test
    public void testHomePage() throws Exception {
        List<Recruiter> expectedRecruiters = createRecruiters(10);
        RecruiterRepository mockRepository = mock(RecruiterRepository.class);
        when(mockRepository.findAll()).thenReturn(expectedRecruiters);

        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

    private List<Recruiter> createRecruiters(int total){
        List<Recruiter> recruiters = new ArrayList<>();
        for(int i=0; i < total; i++){
            recruiters.add(new Recruiter("recruiter" + i + "@example.com"));
        }
        return recruiters;
    }

}
