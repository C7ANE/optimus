
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.optimus.appAdmin.domain.Candidate;
import pl.optimus.appAdmin.repository.CandidateRepository;
import pl.optimus.appAdmin.service.EmailService;

import javax.mail.MessagingException;

@Slf4j
@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateRepository candidateRepo;
    private final EmailService emailService;
    public CandidateController(CandidateRepository candidateRepo, EmailService emailService) {
        this.candidateRepo = candidateRepo;
        this.emailService = emailService;
    }




    @PostMapping
    public String saveCandidate(@ModelAttribute Candidate candidateModel, RedirectAttributes redirectAttr) throws MessagingException {
        candidateRepo.save(candidateModel);
        redirectAttr.addFlashAttribute("message", "The form has been sent");
        log.info("create new candidate");
        emailService.sendMessageToUser(candidateModel.getFirstName(),candidateModel.getEmail(),candidateModel.getContent());
        return "message";
    }



}

