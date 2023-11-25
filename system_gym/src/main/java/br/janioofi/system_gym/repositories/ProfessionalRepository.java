package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.professional.ProfessionalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<ProfessionalModel, Long> {
}
