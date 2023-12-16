package br.janioofi.system_gym.domain.models.professional;

import br.janioofi.system_gym.domain.enums.Time;
import br.janioofi.system_gym.domain.enums.Office;

public record ProfessionalDTO(
        Long idProfessional,
        String name,
        String email,
        String phone,
        String cpf,
        Office office,
        String surname,
        Time time,
        Long user
) {
}
