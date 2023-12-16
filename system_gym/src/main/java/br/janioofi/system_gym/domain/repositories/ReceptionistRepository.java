package br.janioofi.system_gym.domain.repositories;

import br.janioofi.system_gym.domain.models.receptionist.ReceptionistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<ReceptionistModel, Long> {
}
