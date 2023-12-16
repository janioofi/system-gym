package br.janioofi.system_gym.domain.services;

import br.janioofi.system_gym.domain.models.email.EmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", url = "http://localhost:8081/sending-email")
public interface EmailServiceClient {

    @PostMapping
    void sendEmail(@RequestBody EmailRequestDTO emailRequestDTO);
}
