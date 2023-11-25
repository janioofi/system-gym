package br.janioofi.system_gym.service;

import br.janioofi.system_gym.model.user.UserModel;
import br.janioofi.system_gym.model.user.UserRegisterDTO;
import br.janioofi.system_gym.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserModel create(UserRegisterDTO user){
        if(this.repository.findByLogin(user.login()) != null) return null;
        UserModel newUser = new UserModel(user.login(), user.password(), user.role());
        return this.repository.save(newUser);
    }

    public List<UserModel> findAll(){
        return repository.findAll();
    }

    public UserModel findById(String id){
        return repository.findById(id).get();
    }
}
