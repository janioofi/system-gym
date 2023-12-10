package br.janioofi.system_gym.models.email;

public record EmailRequestDTO(String emailTo,
                              String subject,
                              String text) {
}