package br.janioofi.system_gym.domain.enums;

public enum UserRole {
    ADMIN("Admin"),
    USER("User");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
