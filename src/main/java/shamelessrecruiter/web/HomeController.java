package shamelessrecruiter.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "homepage"})
public class HomeController {

    @Autowired
    RecruiterRepository recruiterRepository;

    public HomeController(){
    }

    @RequestMapping(method=GET)
    public String home(){

        return "home";
    }
}
