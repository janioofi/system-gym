package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.configurations.TokenService;
import br.janioofi.system_gym.domain.models.user.AuthenticationDTO;
import br.janioofi.system_gym.domain.models.user.LoginResponseDTO;
import br.janioofi.system_gym.domain.models.user.UserModel;
import br.janioofi.system_gym.domain.models.user.UserRegisterDTO;
import br.janioofi.system_gym.domain.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "API User")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}