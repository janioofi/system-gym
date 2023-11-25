package br.janioofi.system_gym.services;

import br.janioofi.system_gym.models.instructor.InstructorDTO;
import br.janioofi.system_gym.models.instructor.InstructorModel;
import br.janioofi.system_gym.models.professional.ProfessionalModel;
import br.janioofi.system_gym.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {
    private final InstructorRepository repository;
    private final ProfessionalService professionalService;

    public InstructorService(InstructorRepository repository, ProfessionalService service) {
        this.repository = repository;
        this.professionalService = service;
    }

    public InstructorModel create(InstructorDTO instructorDTO){
        InstructorModel data = new InstructorModel();
        ProfessionalModel professional = new ProfessionalModel();
        if(professionalService.findById(instructorDTO.professional()) != null){
            professional = professionalService.findById(instructorDTO.professional());
        }
        data.setCref(instructorDTO.cref());
        data.setProfessional(professional);
        return repository.save(data);
    }
}
