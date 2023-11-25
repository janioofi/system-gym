package br.janioofi.system_gym.services;

import br.janioofi.system_gym.exception.BusinessException;
import br.janioofi.system_gym.exception.RecordNotFoundException;
import br.janioofi.system_gym.models.user.UserModel;
import br.janioofi.system_gym.models.user.UserRegisterDTO;
import br.janioofi.system_gym.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserModel create(UserRegisterDTO user){
        if(this.repository.findByLogin(user.login()) != null) {
            throw new BusinessException("This user already exists");
        }
        UserModel newUser = new UserModel(user.login(), user.password(), user.role());
        return this.repository.save(newUser);
    }

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteById(Long id){
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
