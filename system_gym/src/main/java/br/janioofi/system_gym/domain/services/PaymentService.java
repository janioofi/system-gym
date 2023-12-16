package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.client.ClientModel;
import br.janioofi.system_gym.domain.models.payment.PaymentDTO;
import br.janioofi.system_gym.domain.models.payment.PaymentModel;
import br.janioofi.system_gym.domain.models.payment.PaymentResponse;
import br.janioofi.system_gym.domain.models.plan.PlanModel;
import br.janioofi.system_gym.domain.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.domain.repositories.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final ClientService clientService;
    private final PlanService planService;
    private final ReceptionistService receptionistService;
    private final AuditService auditService;
    private final ModelMapper mapper;

    public PaymentService(PaymentRepository repository, ClientService clientService, PlanService planService, ReceptionistService receptionistService, AuditService auditService, ModelMapper mapper) {
        this.repository = repository;
        this.clientService = clientService;
        this.planService = planService;
        this.receptionistService = receptionistService;
        this.auditService = auditService;
        this.mapper = mapper;
    }

    public List<PaymentResponse> findAll(){
        return repository.findAll().stream().map(e -> mapper.map(e, PaymentResponse.class)).collect(Collectors.toList());
    }

    public PaymentResponse create(PaymentDTO paymentDTO){
        ClientModel client = new ClientModel();
        ReceptionistModel receptionist = new ReceptionistModel();
        PlanModel plan = new PlanModel();
        AuditModel audit = new AuditModel();

        if(clientService.findById(paymentDTO.client()) != null &&
                planService.findById(paymentDTO.plan()) != null &&
                receptionistService.findById(paymentDTO.receptionist()) != null){
            client = clientService.findById(paymentDTO.client());
            receptionist = receptionistService.findById(paymentDTO.receptionist());
            plan = planService.findById(paymentDTO.plan());
        }

        PaymentModel payment = new PaymentModel();
        payment.setDatePayment(LocalDateTime.now());
        payment.setClient(client);
        payment.setPlan(plan);
        payment.setReceptionist(receptionist);
        payment.setValue(paymentDTO.value());
        payment.setMonthPayment(paymentDTO.monthPayment());
        payment.setFormPayment(paymentDTO.formPayment());

        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }
        audit.setDate(LocalDateTime.now());
        audit.setClassJava(ClientService.class.toString());
        audit.setDescription("Trying to make a payment with the plan: " + plan.getName() + ", for the client: " + client.getName());
        audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        auditService.create(audit);
        repository.save(payment);
        PaymentResponse paymentResponse = mapper.map(payment, PaymentResponse.class);
        return paymentResponse;
    }
}
