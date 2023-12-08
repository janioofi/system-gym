package br.janioofi.system_gym.models.payment;

import br.janioofi.system_gym.models.client.ClientModel;
import br.janioofi.system_gym.models.enums.FormPayment;
import br.janioofi.system_gym.models.plan.PlanModel;
import br.janioofi.system_gym.models.receptionist.ReceptionistModel;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payment")
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long  idPayment;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    @Column(name = "date_payment", nullable = false)
    private LocalDateTime datePayment;

    @NotNull
    @Column(nullable = false, precision=8, scale=2)
    private BigDecimal value;

    @NotNull
    @Column(name = "month_payment", length = 2)
    private Integer monthPayment;

    @NotNull
    @Column(name = "form_payment", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormPayment formPayment;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_client")
    @Enumerated(EnumType.ORDINAL)
    private ClientModel client;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_receptionist")
    @Enumerated(EnumType.ORDINAL)
    private ReceptionistModel receptionist;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_plan")
    @Enumerated(EnumType.ORDINAL)
    private PlanModel plan;

    public PaymentModel(BigDecimal value, FormPayment formPayment, ClientModel client, ReceptionistModel receptionist, PlanModel plan, Integer monthPayment) {
        this.value = value;
        this.formPayment = formPayment;
        this.client = client;
        this.receptionist = receptionist;
        this.plan = plan;
        this.monthPayment = monthPayment;
    }

    public PaymentModel() {
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public LocalDateTime getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDateTime datePayment) {
        this.datePayment = datePayment;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public FormPayment getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(FormPayment formPayment) {
        this.formPayment = formPayment;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public PlanModel getPlan() {
        return plan;
    }

    public void setPlan(PlanModel plan) {
        this.plan = plan;
    }

    public ReceptionistModel getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(ReceptionistModel receptionist) {
        this.receptionist = receptionist;
    }

    public Integer getMonthPayment() {
        return monthPayment;
    }

    public void setMonthPayment(Integer monthPayment) {
        this.monthPayment = monthPayment;
    }
}
