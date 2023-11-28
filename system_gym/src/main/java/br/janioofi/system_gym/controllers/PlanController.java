package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.models.plan.PlanDTO;
import br.janioofi.system_gym.models.plan.PlanModel;
import br.janioofi.system_gym.services.PlanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plan", description = "API Plan")
@RestController
@RequestMapping("/v1/plan")
public class PlanController {
    private final PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlanModel> findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanModel create(@RequestBody PlanDTO plan){
        return service.create(plan);
    }
}
