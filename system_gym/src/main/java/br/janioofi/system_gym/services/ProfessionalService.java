package br.janioofi.system_gym.services;

import br.janioofi.system_gym.exception.RecordNotFoundException;
import br.janioofi.system_gym.models.professional.ProfessionalDTO;
import br.janioofi.system_gym.models.professional.ProfessionalModel;
import br.janioofi.system_gym.models.user.UserModel;
import br.janioofi.system_gym.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessionalService {
    private final ProfessionalRepository repository;
    private final UserService userService;

    public ProfessionalService(ProfessionalRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public List<ProfessionalModel> findAll(){
        return repository.findAll();
    }

    public ProfessionalModel create(ProfessionalDTO professional){
        ProfessionalModel data = new ProfessionalModel();
        UserModel user = new UserModel();
        if(userService.findById(professional.user()) != null){
            user = userService.findById(professional.user());
        }

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
        return repository.save(data);
    }

    public ProfessionalModel findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }
}
