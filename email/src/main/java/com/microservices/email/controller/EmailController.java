package com.microservices.email.controller;

import com.microservices.email.dto.*;
import com.microservices.email.model.Email;
import com.microservices.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable String id){
        return ResponseEntity.ok(this.emailService.getById(id));
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<Email> getEmailByEmail(@PathVariable String email){
        return ResponseEntity.ok(this.emailService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<EmailDto> createEmail(@RequestBody CreateEmailRequest emailRequest){
        return ResponseEntity.ok(this.emailService.createEmail(emailRequest));
    }

    @PostMapping("/verify/{id}")
    public ResponseEntity<EmailDto> verify(@PathVariable String id, @RequestBody VerifyEmailRequest verifyRequest){
        return ResponseEntity.ok(this.emailService.verifyEmail(id, verifyRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailDto> updateEmail(@PathVariable String id,@RequestBody UpdateEmailRequest emailRequest){
        return ResponseEntity.ok(this.emailService.updateEmail(id, emailRequest));
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<Boolean> changeEmail(@PathVariable String id, @RequestBody ChangeEmailRequest emailRequest){
        return ResponseEntity.ok(this.emailService.changeEmail(id, emailRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmail(@PathVariable String id){
        return ResponseEntity.ok(this.emailService.deleteEmail(id));
    }
}
