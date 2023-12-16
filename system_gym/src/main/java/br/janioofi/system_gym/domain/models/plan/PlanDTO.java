package br.janioofi.system_gym.domain.models.plan;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PlanDTO(String name, BigDecimal price, LocalDate effectiveDate) {
}
