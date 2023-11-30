package br.janioofi.system_gym.services;

import br.janioofi.system_gym.exception.BusinessException;
import br.janioofi.system_gym.models.audit.AuditModel;
import br.janioofi.system_gym.models.client.ClientModel;
import br.janioofi.system_gym.models.payment.PaymentDTO;
import br.janioofi.system_gym.models.payment.PaymentModel;
import br.janioofi.system_gym.models.plan.PlanModel;
import br.janioofi.system_gym.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final ClientService clientService;
    private final PlanService planService;
    private final ReceptionistService receptionistService;
    private final AuditService auditService;

    public PaymentService(PaymentRepository repository, ClientService clientService, PlanService planService, ReceptionistService receptionistService, AuditService auditService) {
        this.repository = repository;
        this.clientService = clientService;
        this.planService = planService;
        this.receptionistService = receptionistService;
        this.auditService = auditService;
    }

    public List<PaymentModel> findAll(){
        return repository.findAll();
    }

    public PaymentModel create(PaymentDTO paymentDTO){
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
        return repository.save(payment);
    }
}