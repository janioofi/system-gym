package br.janioofi.system_gym.models.professional;

import br.janioofi.system_gym.models.enums.TimeRole;

public record ProfessionalDTO(
        Long idProfessional,
        String name,
        String email,
        String phone,
        String cpf,
        String office,
        String surname,
        TimeRole time,
        Long user
) {
}
