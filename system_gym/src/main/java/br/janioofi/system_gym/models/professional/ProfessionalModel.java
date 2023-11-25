package br.janioofi.system_gym.models.professional;

import br.janioofi.system_gym.models.enums.TimeRole;
import br.janioofi.system_gym.models.user.UserModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_professional")
public class ProfessionalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professional")
    private Long idProfessional;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String phone;

    @NotEmpty
    @NotNull
    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "admission_date",nullable = false)
    private LocalDate admissionDate;

    @Column(name = "updated_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedDate;

    @NotEmpty(message = "Office")
    @NotNull
    @Column(nullable = false)
    private String office;

    private String surname;

    @Column(name = "time_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private TimeRole time;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_user")
    @Enumerated(EnumType.ORDINAL)
    private UserModel user;

    public ProfessionalModel() {
    }

    public ProfessionalModel(String name, String email, String phone, String cpf, String office, String surname, TimeRole time) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.office = office;
        this.surname = surname;
        this.time = time;
    }

    public Long getIdProfessional() {
        return idProfessional;
    }

    public void setIdProfessional(Long idProfessional) {
        this.idProfessional = idProfessional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public TimeRole getTime() {
        return time;
    }

    public void setTime(TimeRole time) {
        this.time = time;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
