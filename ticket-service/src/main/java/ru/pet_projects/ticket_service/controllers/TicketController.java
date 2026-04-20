package ru.pet_projects.ticket_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.mappers.TicketMapper;
import ru.pet_projects.ticket_service.services.TicketService;
import ru.pet_projects.ticket_service.utils.TicketComparator;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketController(TicketService ticketService,
                            TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping("/")
    public List<TicketDto> getAll(@RequestParam(name = "sort",
            required = false, defaultValue = "startDate") String sortingField){
        return ticketService
                .findAll()
                .stream()
                .sorted(TicketComparator.getComparator(sortingField))
                .map(ticketMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TicketDto getById(@PathVariable Long id ){
        return ticketMapper.toDto(ticketService.findById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@RequestBody TicketDto ticketDto){
        return ticketService.save(ticketMapper.toEntity(ticketDto));
    }

    @PutMapping("/{id}")
    public Ticket update(@RequestBody TicketDto ticketDto, @PathVariable Long id){
        return ticketService.update(ticketMapper.toEntity(ticketDto), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        ticketService.deleteById(id);
    }
}