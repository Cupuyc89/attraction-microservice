package ru.pet_projects.ticket_service.services;

import ru.pet_projects.ticket_service.entities.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();
    Ticket findById(Long id);
    Ticket save(Ticket ticket);
    Ticket update(Ticket ticket, Long id);
    void deleteById(long id);
}
