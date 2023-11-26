package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.audit.AuditModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<AuditModel, UUID> {
}
