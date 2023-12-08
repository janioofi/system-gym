package br.janioofi.system_gym.models.client;

import br.janioofi.system_gym.models.plan.PlanModel;
import br.janioofi.system_gym.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.models.user.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_client")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String name;

    @CPF
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String cpf;

    @Past
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "register_date")
    private LocalDate registerDate;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @NotNull
    @Column(length = 2)
    private Integer dueDate;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    private Boolean active;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_receptionist")
    @Enumerated(EnumType.ORDINAL)
    @JsonIgnore
    private ReceptionistModel receptionist;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_plan")
    @Enumerated(EnumType.ORDINAL)
    private PlanModel plan;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_user")
    @Enumerated(EnumType.ORDINAL)
    private UserModel user;

    public ClientModel() {
    }

    public ClientModel(String name, String cpf, LocalDate birthDate, LocalDate registerDate, LocalDateTime updatedDate, Integer dueDate, String email, Boolean active, ReceptionistModel receptionist, PlanModel plan, UserModel user) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
        this.dueDate = dueDate;
        this.email = email;
        this.active = active;
        this.receptionist = receptionist;
        this.plan = plan;
        this.user = user;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getDueDate() {
        return dueDate;
    }

    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ReceptionistModel getReceptionist() {
        return receptionist;
    }

    public void setReceptionist(ReceptionistModel receptionist) {
        this.receptionist = receptionist;
    }

    public PlanModel getPlan() {
        return plan;
    }

    public void setPlan(PlanModel plan) {
        this.plan = plan;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
