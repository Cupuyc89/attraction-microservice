package ru.pet_projects.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet_projects.order_service.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
