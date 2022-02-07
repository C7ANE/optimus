package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.optimus.appAdmin.domain.Register;
import pl.optimus.appAdmin.domain.UplodedFile;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String homeView(Model model){
        model.addAttribute("registerModel",new Register());
        model.addAttribute("uplodedFile", new UplodedFile());
        log.info("open Home broswer");
        return "register_form";
    }

}
