package br.janioofi.system_gym.domain.enums;

public enum Time {
    DAY("Day"),
    NIGHT("Night");

    private final String role;

    Time(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
