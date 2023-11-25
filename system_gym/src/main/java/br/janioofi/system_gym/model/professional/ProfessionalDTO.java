package br.janioofi.system_gym.model.professional;

import java.time.LocalDate;

public record ProfessionalDTO(
        Long idProfessional,
        String name,
        String email,
        String phone,
        String cpf,
        String office,
        String surname,
        TimeRole time
) {
}
