package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.exceptions.RecordNotFoundException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.email.EmailRequestDTO;
import br.janioofi.system_gym.domain.models.professional.ProfessionalDTO;
import br.janioofi.system_gym.domain.models.professional.ProfessionalModel;
import br.janioofi.system_gym.domain.models.user.UserModel;
import br.janioofi.system_gym.domain.repositories.ProfessionalRepository;
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
    private final EmailServiceClient emailServiceClient;

    public ProfessionalService(ProfessionalRepository repository, UserService userService, AuditService auditService, EmailServiceClient emailServiceClient) {
        this.repository = repository;
        this.userService = userService;
        this.auditService = auditService;
        this.emailServiceClient = emailServiceClient;
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
        EmailRequestDTO emailRequest = new EmailRequestDTO(data.getEmail(),"Bem-vindo(a) " + data.getSurname(),"Seja bem-vindo Sr(a) " + data.getName() + ", obrigado por entrar para nosso time de profissionais, seu cargo é: " + data.getOffice() + ", e seu turno é: " + data.getTime());
        emailServiceClient.sendEmail(emailRequest);
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
