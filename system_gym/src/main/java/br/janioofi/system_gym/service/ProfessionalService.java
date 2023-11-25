package br.janioofi.system_gym.service;

import br.janioofi.system_gym.model.professional.ProfessionalDTO;
import br.janioofi.system_gym.model.professional.ProfessionalModel;
import br.janioofi.system_gym.repository.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessionalService {
    private final ProfessionalRepository repository;

    public ProfessionalService(ProfessionalRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalModel> findAll(){
        return repository.findAll();
    }

    public ProfessionalModel create(ProfessionalDTO professional){
        ProfessionalModel data = new ProfessionalModel();

        data.setName(professional.name());
        data.setEmail(professional.email());
        data.setPhone(professional.phone());
        data.setCpf(professional.cpf());
        data.setTime(professional.time());
        data.setSurname(professional.surname());
        data.setOffice(professional.office());
        data.setAdmissionDate(LocalDate.now());
        data.setUpdatedDate(LocalDateTime.now());
        return repository.save(data);
    }
}
