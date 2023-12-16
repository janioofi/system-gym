package br.janioofi.system_gym.domain.repositories;

import br.janioofi.system_gym.domain.models.payment.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}
