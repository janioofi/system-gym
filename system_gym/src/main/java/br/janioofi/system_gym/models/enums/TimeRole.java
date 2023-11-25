package br.janioofi.system_gym.models.enums;

public enum TimeRole {
    DAY("Day"),
    NIGHT("Night");

    private final String role;

    TimeRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
