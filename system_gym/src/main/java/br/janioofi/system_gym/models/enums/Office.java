package br.janioofi.system_gym.models.enums;

public enum Office {
    RECEPTIONIST("Receptionist"),
    INSTRUCTOR("Instructor");

    private final String role;

    Office(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
