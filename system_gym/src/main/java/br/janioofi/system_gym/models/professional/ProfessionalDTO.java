package br.janioofi.system_gym.models.professional;

import br.janioofi.system_gym.models.enums.Office;
import br.janioofi.system_gym.models.enums.Time;

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
