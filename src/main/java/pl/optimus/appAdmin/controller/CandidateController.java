
package pl.optimus.appAdmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.optimus.appAdmin.domain.Candidate;
import pl.optimus.appAdmin.repository.CandidateRepository;
import pl.optimus.appAdmin.service.EmailService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

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
    public String saveCandidate(@ModelAttribute Candidate candidatModel, RedirectAttributes redirectAttr,
                                @RequestParam("attachment")MultipartFile multipartFile) throws MessagingException, UnsupportedEncodingException {
        candidateRepo.save(candidatModel);

        redirectAttr.addFlashAttribute("message", "The form has been sent");
        log.info("create new candidate");
        log.error("Creating a new user failed ");

        emailService.sendMessageToUser(candidatModel.getFirstName(),candidatModel.getEmail(),candidatModel.getContent());
        log.info("sent email to the user");
        log.error("Failed to send the message to the user");

        emailService.sendMessageToClient(candidatModel.getFirstName(),candidatModel.getLastName(),candidatModel.getEmail(),
                candidatModel.getContent(),multipartFile);
        log.info("sent email to the Client");
        log.error("Failed to send the message to the Client");
        return "message";
    }



}

