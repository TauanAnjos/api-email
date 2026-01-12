package com.api_email.api_email.dtos;

import java.util.List;

public record BrevoEmailRequest(BrevoSender sender,
                                List<BrevoTo> to,
                                String subject,
                                String htmlContent) {
}
