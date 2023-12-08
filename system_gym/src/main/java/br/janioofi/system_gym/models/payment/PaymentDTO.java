package br.janioofi.system_gym.models.payment;

import br.janioofi.system_gym.models.enums.FormPayment;

import java.math.BigDecimal;

public record PaymentDTO(BigDecimal value,
                         FormPayment formPayment,
                         Integer monthPayment,
                         Long client,
                         Long receptionist,
                         Long plan) {
}
