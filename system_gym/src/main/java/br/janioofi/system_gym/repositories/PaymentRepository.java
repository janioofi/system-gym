package br.janioofi.system_gym.repositories;

import br.janioofi.system_gym.models.payment.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}
