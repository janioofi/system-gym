package br.janioofi.system_gym.domain.models.audit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
public class AuditModel {

    @Id
    @Column(name = "id_audit")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAudit;

    @NotNull
    private String description;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Column(name = "class")
    private String classJava;

    @NotNull
    @Column(name = "local_adress")
    private String localAdress;

    public AuditModel() {
    }

    public AuditModel(String description, LocalDateTime date, String classJava, String localAdress) {
        this.description = description;
        this.date = date;
        this.classJava = classJava;
        this.localAdress = localAdress;
    }

    public String getClassJava() {
        return classJava;
    }

    public void setClassJava(String classJava) {
        this.classJava = classJava;
    }

    public UUID getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(UUID idAudit) {
        this.idAudit = idAudit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLocalAdress() {
        return localAdress;
    }

    public void setLocalAdress(String localAdress) {
        this.localAdress = localAdress;
    }
}
