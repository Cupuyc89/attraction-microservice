package ru.pet_projects.ticket_service.services;

import ru.pet_projects.ticket_service.dtos.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> findAll(String sortingField);
    TicketDto findById(Long id);
    TicketDto save(TicketDto ticketDto);
    TicketDto update(TicketDto ticketDto, Long id);
    void deleteById(long id);
}
