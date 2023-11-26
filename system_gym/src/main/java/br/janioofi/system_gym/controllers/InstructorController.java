package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.models.instructor.InstructorDTO;
import br.janioofi.system_gym.models.instructor.InstructorModel;
import br.janioofi.system_gym.services.InstructorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Instructor", description = "API Instructor")
@RestController
@RequestMapping("/v1/instructor")
public class InstructorController {

    private final InstructorService service;

    public InstructorController(InstructorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorModel create(@RequestBody InstructorDTO instructor){
        return service.create(instructor);
    }
}
