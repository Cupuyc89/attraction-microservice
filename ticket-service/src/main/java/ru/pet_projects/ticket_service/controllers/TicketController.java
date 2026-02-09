package ru.pet_projects.ticket_service.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.repository.TicketRepository;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/")
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id ){
        return ticketRepository
                .findById(id)
                .orElseThrow(( ) ->
                        new EntityNotFoundException("Ticket is not found."));
    }
}