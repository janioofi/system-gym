package br.janioofi.system_gym.models.instructor;

import br.janioofi.system_gym.models.professional.ProfessionalModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_instructor")
@JsonIgnoreProperties
public class InstructorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor")
    private Long idInstructor;

    @NotBlank
    @Column(length = 6, nullable = false, unique = true)
    private String cref;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_professional")
    @Enumerated(EnumType.ORDINAL)
    private ProfessionalModel professional;

    public InstructorModel(String cref, ProfessionalModel professional) {
        this.cref = cref;
        this.professional = professional;
    }

    public InstructorModel() {
    }

    public Long getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Long idInstructor) {
        this.idInstructor = idInstructor;
    }

    public String getCref() {
        return cref;
    }

    public void setCref(String cref) {
        this.cref = cref;
    }

    public ProfessionalModel getProfessional() {
        return professional;
    }

    public void setProfessional(ProfessionalModel professional) {
        this.professional = professional;
    }
}
