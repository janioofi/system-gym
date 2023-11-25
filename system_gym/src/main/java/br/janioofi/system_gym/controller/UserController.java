package br.janioofi.system_gym.controller;

import br.janioofi.system_gym.model.user.UserModel;
import br.janioofi.system_gym.model.user.UserRegisterDTO;
import br.janioofi.system_gym.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<UserModel> create(@RequestBody UserRegisterDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(user));
    }
}
