package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.repositories.AuditRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    public AuditModel create(AuditModel audit){
        return repository.save(audit);
    }
}
