package ru.pet_projects.ticket_service.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@RequestBody Ticket ticket){
        return ticketRepository.save(ticket);
    }

    @PatchMapping("/{id}")
    public Ticket update(@RequestBody Ticket ticket, @PathVariable Long id){
        return ticketRepository.findById(id)
                .map(s -> {
                    s.setExcursionId(ticket.getExcursionId());
                    s.setPrice(ticket.getPrice());
                    s.setDateOfStartExcursion(ticket.getDateOfStartExcursion());
                    s.setDateOfEndExcursion(ticket.getDateOfEndExcursion());
                    s.setBooking(ticket.getBooking());
                    return ticketRepository.save(s);
                })
                .orElseThrow(() ->
                        new EntityNotFoundException("Ticket is not found."));
    }
}