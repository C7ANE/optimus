package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.optimus.appAdmin.domain.Candidate;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("candidateModel",new Candidate());
        log.info("open Home broswer");
        return "index";
    }

}
