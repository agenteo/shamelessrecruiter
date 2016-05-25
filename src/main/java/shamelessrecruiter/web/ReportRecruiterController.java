package shamelessrecruiter.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/create")
public class ReportRecruiterController {
    private RecruiterRepository recruiterRepository;

    @Autowired
    public ReportRecruiterController(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    // Approach taken from https://spring.io/guides/gs/validating-form-input/
    @RequestMapping(method = POST)
    public String create(@Valid ReportRecruiterForm reportRecruiterForm,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reportRecruiterForm", reportRecruiterForm);
            return "home";
        }
        recruiterRepository.save(new Recruiter(reportRecruiterForm.getName(),
                reportRecruiterForm.getEmail(),
                reportRecruiterForm.getMessage()));
        return "recruiterReportCreated";
    }
}
