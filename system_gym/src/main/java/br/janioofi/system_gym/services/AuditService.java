package br.janioofi.system_gym.services;

import br.janioofi.system_gym.models.audit.AuditModel;
import br.janioofi.system_gym.repositories.AuditRepository;
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
