package br.janioofi.system_gym.models.enums;

public enum UserRole {
    ADMIN("Admin"),
    PROFESSIONAL("Professional"),
    CLIENT("Client");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
