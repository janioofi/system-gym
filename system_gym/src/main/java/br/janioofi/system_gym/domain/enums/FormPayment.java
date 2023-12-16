package br.janioofi.system_gym.domain.enums;

public enum FormPayment {
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    MONEY("Money"),
    PIX("Pix");

    private final String role;

    FormPayment(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
