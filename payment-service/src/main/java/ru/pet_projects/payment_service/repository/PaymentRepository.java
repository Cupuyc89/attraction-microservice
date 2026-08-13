package ru.pet_projects.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_projects.payment_service.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    Payment findByOrderId(Long orderId);
}
