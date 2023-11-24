package br.janioofi.system_gym.service;

import br.janioofi.system_gym.model.user.UserModel;
import br.janioofi.system_gym.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserModel create(UserModel user){
        return repository.save(user);
    }

    public List<UserModel> findAll(){
        return repository.findAll();
    }
}
