package shamelessrecruiter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import shamelessrecruiter.web.Recruiter;
import shamelessrecruiter.web.RecruiterRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/listRecruiters")
public class ListRecruitersApiController {
    private RecruiterRepository recruiterRepository;

    @Autowired
    public ListRecruitersApiController (RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @RequestMapping(method = GET)
    public List<Recruiter> index(){
        return recruiterRepository.findAll();
    }
}
