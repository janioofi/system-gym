package br.janioofi.system_gym.services;

import br.janioofi.system_gym.exception.BusinessException;
import br.janioofi.system_gym.exception.RecordNotFoundException;
import br.janioofi.system_gym.models.audit.AuditModel;
import br.janioofi.system_gym.models.professional.ProfessionalDTO;
import br.janioofi.system_gym.models.professional.ProfessionalModel;
import br.janioofi.system_gym.models.user.UserModel;
import br.janioofi.system_gym.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessionalService {
    private final ProfessionalRepository repository;
    private final UserService userService;
    private final AuditService auditService;

    public ProfessionalService(ProfessionalRepository repository, UserService userService, AuditService auditService) {
        this.repository = repository;
        this.userService = userService;
        this.auditService = auditService;
    }

    public List<ProfessionalModel> findAll(){
        return repository.findAll();
    }

    public ProfessionalModel create(ProfessionalDTO professional){
        AuditModel audit = new AuditModel();
        ProfessionalModel data = new ProfessionalModel();
        UserModel user = new UserModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }

        if(userService.findById(professional.user()) != null){
            user = userService.findById(professional.user());
        }

        try{
            data.setName(professional.name());
            data.setEmail(professional.email());
            data.setPhone(professional.phone());
            data.setCpf(professional.cpf());
            data.setTime(professional.time());
            data.setSurname(professional.surname());
            data.setOffice(professional.office());
            data.setAdmissionDate(LocalDate.now());
            data.setUpdatedDate(LocalDateTime.now());
            data.setUser(user);
            audit.setDescription("Trying to create an professional with user id: " + data.getUser().getIdUser());
        }catch (Exception e){
            audit.setDescription("Failed when trying to create a new professional, error: " + e.getMessage());
        }finally {
            audit.setDate(LocalDateTime.now());
            audit.setClassJava(ProfessionalService.class.toString());
            audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
            auditService.create(audit);
        }
        return repository.save(data);
    }

    public ProfessionalModel findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id){
        String ipMachine;
        AuditModel audit = new AuditModel();
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }
        audit.setDate(LocalDateTime.now());
        audit.setClassJava(ProfessionalService.class.toString());
        audit.setDescription("Trying to delete a professional with id: " + id);
        audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        auditService.create(audit);
        repository.delete(findById(id));
    }
}
