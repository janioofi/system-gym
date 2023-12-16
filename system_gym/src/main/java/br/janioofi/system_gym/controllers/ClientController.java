package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.domain.models.client.ClientDTO;
import br.janioofi.system_gym.domain.models.client.ClientModel;
import br.janioofi.system_gym.domain.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client", description = "API Client")
@RestController
@RequestMapping("/v1/client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientModel> findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientModel create(@RequestBody @Valid ClientDTO client){
        return service.create(client);
    }

    @GetMapping("/{id}")
    public ClientModel findById(@PathVariable Long id){
        return service.findById(id);
    }
}
