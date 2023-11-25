package br.janioofi.system_gym.models.user;

import br.janioofi.system_gym.models.enums.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
