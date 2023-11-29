package br.janioofi.system_gym.models.client;

import java.time.LocalDate;

public record ClientDTO(
        String name,
        String cpf,
        LocalDate birthDate,
        String dueDate,
        Long receptionist,
        Long plan,
        Long user
) {
}
