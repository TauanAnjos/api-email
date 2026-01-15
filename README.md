# 📧 API de Envio de E-mails

Essa é uma **API de envio de e-mails** desenvolvida em **Java** com **Spring Boot**, permitindo enviar e-mails personalizados usando **templates Thymeleaf** ou **HTML inline**. Ela se integra com o **Brevo (ex-Sendinblue)** via SMTP e API, garantindo envio confiável e seguro.

## 🚀 Funcionalidades

* Envio de e-mails com **templates Thymeleaf** ou HTML inline.
* Substituição de variáveis nos templates (ex: `${nome}`).
* Integração com **Brevo** para envio de e-mails.
* Endpoint de **health check** (`/email/health`) para monitoramento da API.
* Validação de entrada de dados (e-mails válidos, campos obrigatórios).

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21+
* **Framework:** Spring Boot (REST, Validation, Dependency Injection)
* **Template Engine:** Thymeleaf
* **Cliente HTTP reativo:** WebClient (para chamada à API Brevo)
* **Envio de e-mails SMTP:** JavaMailSender
* **Deploy:** Render
* **Validação de entrada:** Jakarta Validation (`@Email`, `@NotBlank`)
* **Documentação:** Swagger (anotações nos DTOs)

## 🔗 Endpoints Disponíveis

| Método | Endpoint        | Descrição                                                                                    |
| ------ | --------------- | -------------------------------------------------------------------------------------------- |
| `POST` | `/email/send`   | Envia um e-mail. Recebe JSON com destinatário, assunto, template ou HTML inline e variáveis. |
| `GET`  | `/email/health` | Retorna status da API (`{"status": "UP"}`) para monitoramento.                               |

## 📋 Exemplos de Payload

### Com template Thymeleaf

```json
{
  "to": "usuario@email.com",
  "subject": "Teste de envio",
  "template": "email",
  "templateHtmlInline": null,
  "variables": {
    "nome": "Tauan Anjos"
  }
}
```

### Com HTML inline (sobrescreve o template)

```json
{
  "to": "usuario@email.com",
  "subject": "Teste de envio",
  "template": null,
  "templateHtmlInline": "<h1>Olá {{nome}}</h1><p>Este é um teste da API de e-mails.</p>",
  "variables": {
    "nome": "Tauan Anjos"
  }
}
```

## ⚡ Como Testar a API

1. Acesse o link da API deployada no Render:

```
https://seu-api-email.onrender.com
```

2. Para enviar um e-mail, faça um `POST` para:

```
https://seu-api-email.onrender.com/email/send
```

com o JSON conforme o exemplo acima.

3. Para verificar se a API está funcionando, faça um `GET` em:

```
https://seu-api-email.onrender.com/email/health
```

Resposta esperada:

```json
{
  "status": "UP"
}
```

## 📝 Observações

* Se o campo **`templateHtmlInline`** estiver preenchido, ele **substitui o template Thymeleaf**.
* Variáveis no template devem estar no formato `${variavel}` no HTML.
* As credenciais sensíveis (`BREVO_API_KEY`, `EMAIL_USERNAME`, `EMAIL_PASSWORD`) devem estar configuradas como **environment variables** no Render.
* Ideal para **testes de envio de e-mails** ou integração com sistemas que precisem disparar notificações por e-mail.
