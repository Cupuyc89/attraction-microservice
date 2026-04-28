package ru.pet_projects.ticket_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.ticket_service.dtos.TicketDto;
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
    public List<TicketDto> getAll(@RequestParam(name = "sort",
            required = false, defaultValue = "startDate") String sortingField){
        return ticketService.findAll(sortingField);
    }

    @GetMapping("/{id}")
    public TicketDto getById(@PathVariable Long id ){
        return ticketService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDto create(@RequestBody TicketDto ticketDto){
        return ticketService.save(ticketDto);
    }

    @PutMapping("/{id}")
    public TicketDto update(@RequestBody TicketDto ticketDto, @PathVariable Long id){
        return ticketService.update(ticketDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        ticketService.deleteById(id);
    }
}