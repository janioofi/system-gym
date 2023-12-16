package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.exceptions.BusinessException;
import br.janioofi.system_gym.domain.models.audit.AuditModel;
import br.janioofi.system_gym.domain.models.instructor.InstructorDTO;
import br.janioofi.system_gym.domain.models.instructor.InstructorModel;
import br.janioofi.system_gym.domain.models.professional.ProfessionalModel;
import br.janioofi.system_gym.domain.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Service
public class InstructorService {
    private final InstructorRepository repository;
    private final ProfessionalService professionalService;
    private final AuditService auditService;

    public InstructorService(InstructorRepository repository, ProfessionalService service, AuditService auditService) {
        this.repository = repository;
        this.professionalService = service;
        this.auditService = auditService;
    }

    public InstructorModel create(InstructorDTO instructorDTO) {
        AuditModel audit = new AuditModel();
        InstructorModel data = new InstructorModel();
        ProfessionalModel professional = new ProfessionalModel();
        String ipMachine;
        String host;
        try {
            ipMachine = InetAddress.getLocalHost().getHostAddress();
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new BusinessException(e.getMessage());
        }
        try{
            if(professionalService.findById(instructorDTO.professional()) != null){
                professional = professionalService.findById(instructorDTO.professional());
            }
            data.setCref(instructorDTO.cref());
            data.setProfessional(professional);
            audit.setDate(LocalDateTime.now());
            audit.setClassJava(InstructorService.class.toString());
            audit.setDescription("Trying to create an instructor with professional id: " + professional.getIdProfessional());
            audit.setLocalAdress("IP: " + ipMachine + "\n Host: " + host);
        }catch (Exception e){
            audit.setDate(LocalDateTime.now());
            audit.setClassJava(InstructorService.class.toString());
            audit.setDescription("Failed when trying to create a new instructor, error: " + e.getMessage());
            audit.setLocalAdress("IP: " + ipMachine + " Host: " + host);
        }finally {
            auditService.create(audit);
        }
        return repository.save(data);
    }
}
