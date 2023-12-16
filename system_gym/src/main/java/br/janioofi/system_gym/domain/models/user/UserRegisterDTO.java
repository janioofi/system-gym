package br.janioofi.system_gym.domain.models.user;

import br.janioofi.system_gym.domain.enums.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
