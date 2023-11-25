package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.models.professional.ProfessionalDTO;
import br.janioofi.system_gym.models.professional.ProfessionalModel;
import br.janioofi.system_gym.services.ProfessionalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professional")
@Tag(name = "Professional", description = "API Professional")
public class ProfessionalController {
    private final ProfessionalService service;

    public ProfessionalController(ProfessionalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalModel>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ProfessionalModel> create(@RequestBody @Valid ProfessionalDTO professional){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(professional));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
