package com.api_email.api_email.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record EmailRequest (@Email(message = "E-mail inválido")
                            @NotBlank(message = "O e-mail não pode ser vazio")
                            @Schema(description = "E-mail do destinatário", example = "usuario@email.com")
                            String to,
                            @NotBlank(message = "O texto não pode ser vazio")
                            @Schema(description = "Assunto do e-mail", example = "Teste de envio")
                            String subject,
                            @Schema(description = "Nome do template Thymeleaf que será processado", example = "email")
                            String template,
                            @Schema(description = "HTML inline que sobrescreve o template. Se preenchido, o template será ignorado", example = "<h1>Olá {{nome}}</h1>")
                            String templateHtmlInline,
                            @Schema(description = "Variáveis do template, chave-valor", example = "{ \"nome\": \"Tauan Anjos\" }")
                            Map<String, Object> variables){

}
