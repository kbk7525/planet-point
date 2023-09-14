package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.backend.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
