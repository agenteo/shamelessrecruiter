package shamelessrecruiter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shamelessrecruiter.web.Recruiter;
import shamelessrecruiter.web.RecruiterRepository;
import shamelessrecruiter.web.ReportRecruiterForm;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/reportRecruiter")
public class ReportRecruiterApiController {
    private RecruiterRepository recruiterRepository;

    @Autowired
    public ReportRecruiterApiController(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    // Experiment to understand what's wrong. Turns outs first: Unitest.field('a', 'b').toJson() is
    // not sending a JSON data but rather a mime FORM field. Not documented very well.
//    @RequestMapping(method = POST)
//    public Map<String, Object> create(@RequestBody ReportRecruiterRequest reportRecruiterRequest){
//        Map<String, Object> response = new HashMap<>();
//        System.out.println(reportRecruiterRequest);
//        response.put("response", "just testing");
//        return response;
//    }
    @RequestMapping(method = POST)
    public Map<String, Object> create(@RequestBody @Valid ReportRecruiterForm reportRecruiterForm,
                         BindingResult bindingResult){
        Map<String, Object> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            // NOTE: in reality convert bindingResult.getAllErrros to JSON
            // response.put("response", bindingResult.getAllErrors());
            System.out.println(bindingResult.toString());
            response.put("response", "bad service");
            return response;
        }
        recruiterRepository.save(new Recruiter(reportRecruiterForm.getName(),
                reportRecruiterForm.getEmail(),
                reportRecruiterForm.getMessage()));
        response.put("response", "good service");
        return response;
    }
}
