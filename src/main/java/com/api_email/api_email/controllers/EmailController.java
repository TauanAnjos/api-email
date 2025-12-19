package com.api_email.api_email.controllers;

import com.api_email.api_email.dtos.EmailRequest;
import com.api_email.api_email.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/email")
@RestController
public class EmailController {
    private EmailService emailService;
    public EmailController (EmailService emailService){
        this.emailService = emailService;
    }
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequest request){
        emailService.sendEmail(request);
        return  ResponseEntity.ok("E-mail recebido para envio");
    }
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health(){
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
