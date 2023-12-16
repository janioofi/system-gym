package br.janioofi.system_gym.domain.repositories;

import br.janioofi.system_gym.domain.models.plan.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanModel, Long> {
}
