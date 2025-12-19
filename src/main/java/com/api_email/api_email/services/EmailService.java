package com.api_email.api_email.services;

import com.api_email.api_email.dtos.EmailRequest;
import com.api_email.api_email.exceptions.EmailServiceException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine){
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    public void sendEmail(EmailRequest request){
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            String html;
            if(request.templateHtmlInline() != null && !request.templateHtmlInline().isBlank()){
                html = request.templateHtmlInline();
            }else{
                Context context = new Context();
                context.setVariables(request.variables());
                html = templateEngine.process(request.template(), context);
            }

            helper.setTo(request.to());
            helper.setSubject(request.subject());
            helper.setText(html, true);

            mailSender.send(mimeMessage);
        }
        catch (MessagingException e){
            throw new EmailServiceException("Falha ao enviar e-mail");
        }
    }
}
