package br.janioofi.system_gym.services;

import br.janioofi.system_gym.exception.BusinessException;
import br.janioofi.system_gym.exception.RecordNotFoundException;
import br.janioofi.system_gym.models.audit.AuditModel;
import br.janioofi.system_gym.models.user.UserModel;
import br.janioofi.system_gym.models.user.UserRegisterDTO;
import br.janioofi.system_gym.repositories.UserRepository;
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

    public UserModel create(UserRegisterDTO user){
        AuditModel audit = new AuditModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }

        if(this.repository.findByLogin(user.login()) != null) {
            throw new BusinessException("This user already exists");
        }
        UserModel newUser = new UserModel(user.login(), user.password(), user.role());
        audit.setDate(LocalDateTime.now());
        audit.setClassJava(UserService.class.toString());
        audit.setDescription("Trying to create a user with login: " + newUser.getLogin());
        audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        auditService.create(audit);
        return this.repository.save(newUser);
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
