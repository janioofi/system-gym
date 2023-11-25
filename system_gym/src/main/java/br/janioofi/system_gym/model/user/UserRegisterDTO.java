package br.janioofi.system_gym.model.user;

public record UserRegisterDTO(String login, String password, UserRole role) {
}
