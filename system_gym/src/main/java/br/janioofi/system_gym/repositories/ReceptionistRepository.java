package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.receptionist.ReceptionistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<ReceptionistModel, Long> {
}
