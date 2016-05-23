package shamelessrecruiter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/create")
public class CreateRecruiterController {
    private RecruiterRepository recruiterRepository;

    @Autowired
    public CreateRecruiterController(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @RequestMapping(method=POST)
    public String create(@RequestParam("email") String email) {
        recruiterRepository.save(new Recruiter(email));
        return "recruiterCreated";
    }
}