package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.domain.models.receptionist.ReceptionistDTO;
import br.janioofi.system_gym.domain.models.receptionist.ReceptionistModel;
import br.janioofi.system_gym.domain.services.ReceptionistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Receptionist", description = "API Receptionist")
@RequestMapping("/v1/receptionist")
public class ReceptionistController {
    private final ReceptionistService service;

    public ReceptionistController(ReceptionistService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReceptionistModel create(@RequestBody ReceptionistDTO receptionistDTO){
        return service.create(receptionistDTO);
    }
}
