package com.api_email.api_email.services;

import com.api_email.api_email.dtos.BrevoEmailRequest;
import com.api_email.api_email.dtos.BrevoSender;
import com.api_email.api_email.dtos.BrevoTo;
import com.api_email.api_email.dtos.EmailRequest;
import com.api_email.api_email.exceptions.EmailServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;


@Service
public class EmailService {
    private final WebClient webClient;
    private final TemplateEngine templateEngine;
    @Value("${spring.brevo.api_key}")
    private String brevoApiKey;
    @Value("${spring.mail.from}")
    private String emailFrom;

    public EmailService( TemplateEngine templateEngine, WebClient webClient){
        this.templateEngine = templateEngine;
        this.webClient = webClient;
    }
    public void sendEmail(EmailRequest request){
        String html;
        if(request.templateHtmlInline() != null && !request.templateHtmlInline().isBlank()){
            html = request.templateHtmlInline();
        }else{
            Context context = new Context();
            context.setVariables(request.variables());
            html = templateEngine.process(request.template(), context);
        }
        BrevoSender sender = new BrevoSender(emailFrom, "Sistema");
        BrevoTo to = new BrevoTo(request.to(), null);

        BrevoEmailRequest req = new BrevoEmailRequest(sender, List.of(to), request.subject(), html);

        webClient.post().uri("https://api.brevo.com/v3/smtp/email")
                .header("api-key", brevoApiKey)
                .bodyValue(req)
                .retrieve().
                toBodilessEntity()
                .block();
    }
}
