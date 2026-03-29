package ru.pet_projects.ticket_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public List<Ticket> getAll(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id ){
        return ticketService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@RequestBody Ticket ticket){
        return ticketService.save(ticket);
    }

    @PutMapping("/{id}")
    public Ticket update(@RequestBody Ticket ticket, @PathVariable Long id){
        return ticketService.update(ticket, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        ticketService.deleteById(id);
    }
}