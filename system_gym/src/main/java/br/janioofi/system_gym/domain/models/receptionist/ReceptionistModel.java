package br.janioofi.system_gym.domain.models.receptionist;

import br.janioofi.system_gym.domain.models.professional.ProfessionalModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_receptionist")
public class ReceptionistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receptionist")
    private Long idReceptionist;

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_professional")
    @Enumerated(EnumType.ORDINAL)
    private ProfessionalModel professional;

    public ReceptionistModel() {
    }

    public ReceptionistModel(ProfessionalModel professional) {
        this.professional = professional;
    }

    public Long getIdReceptionist() {
        return idReceptionist;
    }

    public void setIdReceptionist(Long idReceptionist) {
        this.idReceptionist = idReceptionist;
    }

    public ProfessionalModel getProfessional() {
        return professional;
    }

    public void setProfessional(ProfessionalModel professional) {
        this.professional = professional;
    }
}
