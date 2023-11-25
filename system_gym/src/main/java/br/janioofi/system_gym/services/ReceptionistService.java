package br.janioofi.system_gym.services;

import br.janioofi.system_gym.models.professional.ProfessionalModel;
import br.janioofi.system_gym.models.receptionist.ReceptionistDTO;
import br.janioofi.system_gym.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.repositories.ReceptionistRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistService {
    private final ReceptionistRepository repository;
    private final ProfessionalService professionalService;

    public ReceptionistService(ReceptionistRepository repository, ProfessionalService professionalService) {
        this.repository = repository;
        this.professionalService = professionalService;
    }

    public ReceptionistModel create(ReceptionistDTO receptionistDTO){
        ReceptionistModel data = new ReceptionistModel();
        ProfessionalModel professional = new ProfessionalModel();
        if(professionalService.findById(receptionistDTO.professional()) != null){
            professional = professionalService.findById(receptionistDTO.professional());
        }
        data.setProfessional(professional);
        return repository.save(data);
    }
}
