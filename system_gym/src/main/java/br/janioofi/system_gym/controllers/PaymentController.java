package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.models.payment.PaymentDTO;
import br.janioofi.system_gym.models.payment.PaymentModel;
import br.janioofi.system_gym.models.payment.PaymentResponse;
import br.janioofi.system_gym.services.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Payment", description = "API Payment")
@RequestMapping("/v1/payment")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> findAll(){
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse create(@RequestBody PaymentDTO payment){
        return service.create(payment);
    }
}
