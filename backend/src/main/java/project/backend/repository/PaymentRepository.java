package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(String orderId);

    Optional<Payment> findByPaymentKey(String paymentKey);

    List<Payment> findAllByUserEmail(String email);
}
