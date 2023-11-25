package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.models.user.UserModel;
import br.janioofi.system_gym.models.user.UserRegisterDTO;
import br.janioofi.system_gym.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "API User")
@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<UserModel> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UserModel findByID(@PathVariable @Positive Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserModel create(@RequestBody @Valid UserRegisterDTO user){
        return service.create(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Positive Long id){
        service.deleteById(id);
    }
}
