package pl.optimus.appAdmin.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.optimus.appAdmin.domain.candidate;

@RestController
@RequestMapping("/api/v1")
public class candidateController {

    @GetMapping("/formCandidate")
public String formView(Model model){
        model.addAttribute("candidate",new candidate());
        return "index";
    }


}
