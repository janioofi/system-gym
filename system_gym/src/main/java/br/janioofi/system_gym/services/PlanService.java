package br.janioofi.system_gym.services;

import br.janioofi.system_gym.models.audit.AuditModel;
import br.janioofi.system_gym.models.plan.PlanDTO;
import br.janioofi.system_gym.models.plan.PlanModel;
import br.janioofi.system_gym.repositories.PlanRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlanService {
    private final PlanRepository repository;
    private final AuditService auditService;

    public PlanService(PlanRepository repository, AuditService auditService) {
        this.repository = repository;
        this.auditService = auditService;
    }

    public List<PlanModel> findAll(){
        return repository.findAll();
    }

    public PlanModel create(PlanDTO plan){
        PlanModel data = new PlanModel();
        AuditModel audit = new AuditModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        data.setName(plan.name());
        data.setPrice(plan.price());
        data.setEffectiveDate(plan.effectiveDate());
        audit.setDate(LocalDateTime.now());
        audit.setClassJava(InstructorService.class.toString());
        audit.setDescription("Trying to create a new named plan: " + plan.name());
        audit.setLocalAdress("IP: " + ipMachine + "\n Host: " + host);
        auditService.create(audit);
        return repository.save(data);
    }
}
