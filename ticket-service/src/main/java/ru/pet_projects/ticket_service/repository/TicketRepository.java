package ru.pet_projects.ticket_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet_projects.ticket_service.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
