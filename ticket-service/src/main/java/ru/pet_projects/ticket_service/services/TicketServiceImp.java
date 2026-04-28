package ru.pet_projects.ticket_service.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.mappers.TicketMapper;
import ru.pet_projects.ticket_service.repository.TicketRepository;
import ru.pet_projects.ticket_service.utils.TicketComparator;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService{

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketServiceImp(TicketRepository ticketRepository,
                            TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<TicketDto> findAll(String sortingField) {
        return ticketRepository
                .findAll()
                .stream()
                .sorted(TicketComparator.getComparator(sortingField))
                .map(ticketMapper::toDto)
                .toList();
    }

    @Override
    public TicketDto findById(Long id) {
        return ticketRepository
                .findById(id)
                .map(ticketMapper::toDto)
                .orElseThrow(()->new EntityNotFoundException(""));
    }

    @Override
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = ticketRepository.save(ticketMapper.toEntity(ticketDto));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public TicketDto update(TicketDto ticket, Long id) {
        return ticketRepository.findById(id)
                .map(s -> {
                    s.setExcursionId(ticket.excursionId());
                    s.setPrice(ticket.price());
                    s.setDateOfStartExcursion(ticket.dateOfStartExcursion());
                    s.setDateOfEndExcursion(ticket.dateOfEndExcursion());
                    s.setBooking(ticket.booking());
                    return ticketRepository.save(s);
                })
                .map(ticketMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Ticket is not found."));
    }

    @Override
    public void deleteById(long id) {
        TicketDto ticket = findById(id);
        ticketRepository.deleteById(ticket.id());
    }
}
