package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.exceptions.RecordNotFoundException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.user.UserModel;
import br.janioofi.system_gym.domain.models.user.UserRegisterDTO;
import br.janioofi.system_gym.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final AuditService auditService;

    public UserService(UserRepository repository, AuditService auditService) {
        this.repository = repository;
        this.auditService = auditService;
    }
    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteById(Long id){
        AuditModel audit = new AuditModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }

        audit.setDate(LocalDateTime.now());
        audit.setClassJava(UserService.class.toString());
        audit.setDescription("Trying to deactivate user with id: " + id);
        audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        auditService.create(audit);
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
