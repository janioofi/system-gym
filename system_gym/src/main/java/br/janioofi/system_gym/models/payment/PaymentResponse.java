package br.janioofi.system_gym.models.payment;

import br.janioofi.system_gym.models.enums.FormPayment;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse{
    private BigDecimal value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    private LocalDateTime datePayment;
    private FormPayment formPayment;
    private Long client;
    private Long receptionist;
    private Long plan;

    public PaymentResponse() {
    }

    public PaymentResponse(BigDecimal value, LocalDateTime datePayment, Long client, FormPayment formPayment, Long receptionist, Long plan) {
        this.value = value;
        this.datePayment = datePayment;
        this.client = client;
        this.formPayment = formPayment;
        this.receptionist = receptionist;
        this.plan = plan;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDateTime datePayment) {
        this.datePayment = datePayment;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(FormPayment formPayment) {
        this.formPayment = formPayment;
    }

    public Long getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(Long receptionist) {
        this.receptionist = receptionist;
    }

    public Long getPlan() {
        return plan;
    }

    public void setPlan(Long plan) {
        this.plan = plan;
    }
}
