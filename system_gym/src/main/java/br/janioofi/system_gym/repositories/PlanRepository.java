package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.plan.PlanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlanModel, Long> {
}
