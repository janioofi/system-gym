package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.exceptions.RecordNotFoundException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.client.ClientDTO;
import br.janioofi.system_gym.domain.models.client.ClientModel;
import br.janioofi.system_gym.domain.models.email.EmailRequestDTO;
import br.janioofi.system_gym.domain.models.plan.PlanModel;
import br.janioofi.system_gym.domain.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.domain.models.user.UserModel;
import br.janioofi.system_gym.domain.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repository;
    private final UserService userService;
    private final PlanService planService;
    private final ReceptionistService receptionistService;
    private final AuditService auditService;
    private final EmailServiceClient emailServiceClient;

    public ClientService(ClientRepository repository, UserService userService, PlanService planService, ReceptionistService receptionistService, AuditService auditService, EmailServiceClient emailServiceClient) {
        this.repository = repository;
        this.userService = userService;
        this.planService = planService;
        this.receptionistService = receptionistService;
        this.auditService = auditService;
        this.emailServiceClient = emailServiceClient;
    }

    public List<ClientModel> findAll(){
        return repository.findAll();
    }

    public ClientModel findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public ClientModel create(ClientDTO client){
        AuditModel audit = new AuditModel();
        UserModel user = new UserModel();
        ReceptionistModel receptionist = new ReceptionistModel();
        PlanModel plan = new PlanModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }

        if(userService.findById(client.user()) != null &&
                planService.findById(client.plan()) != null &&
                receptionistService.findById(client.receptionist()) != null){
            user = userService.findById(client.user());
            receptionist = receptionistService.findById(client.receptionist());
            plan = planService.findById(client.plan());
        }
        ClientModel data = new ClientModel();
        data.setRegisterDate(LocalDate.now());
        data.setUpdatedDate(LocalDateTime.now());
        data.setActive(true);
        data.setName(client.name());
        data.setCpf(client.cpf());
        data.setBirthDate(client.birthDate());
        data.setDueDate(client.dueDate());
        data.setUser(user);
        data.setReceptionist(receptionist);
        data.setPlan(plan);
        data.setEmail(client.email());

        audit.setDate(LocalDateTime.now());
        audit.setClassJava(ClientService.class.toString());
        audit.setDescription("Trying to create a new user with name: " + client.name());
        audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        EmailRequestDTO emailRequest = new EmailRequestDTO(data.getEmail(),"Bem-vindo a academia","Seja bem-vindo Sr(a) " + data.getName() + ", Obrigado por confiar no nosso serviço, o plano escolhido pelo senhor foi o " + plan.getName() + ", com valor de R$ " + plan.getPrice());
        emailServiceClient.sendEmail(emailRequest);
        auditService.create(audit);
        repository.save(data);
        return data;
    }
}
