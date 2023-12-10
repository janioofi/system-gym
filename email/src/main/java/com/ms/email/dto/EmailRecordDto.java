package com.ms.email.dto;

import java.util.UUID;

public record EmailRecordDto(String emailTo,
                             String subject,
                             String text) {
}
