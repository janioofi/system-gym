package br.janioofi.system_gym.domain.models.email;

public record EmailRequestDTO(String emailTo,
                              String subject,
                              String text) {
}