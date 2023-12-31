package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.exceptions.RecordNotFoundException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.professional.ProfessionalModel;
import br.janioofi.system_gym.domain.models.receptionist.ReceptionistDTO;
import br.janioofi.system_gym.domain.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.domain.repositories.ReceptionistRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Service
public class ReceptionistService {
    private final ReceptionistRepository repository;
    private final ProfessionalService professionalService;
    private final AuditService auditService;

    public ReceptionistService(ReceptionistRepository repository, ProfessionalService professionalService, AuditService auditService) {
        this.repository = repository;
        this.professionalService = professionalService;
        this.auditService = auditService;
    }

    public ReceptionistModel create(ReceptionistDTO receptionistDTO){
        AuditModel audit = new AuditModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }

        ReceptionistModel data = new ReceptionistModel();
        ProfessionalModel professional = new ProfessionalModel();
        try{
            if(professionalService.findById(receptionistDTO.professional()) != null){
                professional = professionalService.findById(receptionistDTO.professional());
            }
            data.setProfessional(professional);
            audit.setDate(LocalDateTime.now());
            audit.setClassJava(ReceptionistService.class.toString());
            audit.setDescription("Trying to create a receptionist with professional id: " + professional.getIdProfessional());
            audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        }catch (Exception e){
            audit.setDate(LocalDateTime.now());
            audit.setClassJava(ReceptionistService.class.toString());
            audit.setDescription("Failed when trying to create a new receptionist, error: " + e.getMessage());
            audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        }finally {
            auditService.create(audit);
        }
        return repository.save(data);
    }

    public ReceptionistModel findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
