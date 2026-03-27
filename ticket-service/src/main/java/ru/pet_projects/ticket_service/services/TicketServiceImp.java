package ru.pet_projects.ticket_service.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.repository.TicketRepository;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService{

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImp(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketRepository
                .findById(id)
                .orElseThrow(()->new EntityNotFoundException(""));
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket update(Ticket ticket, Long id) {
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

    @Override
    public void deleteById(long id) {
        Ticket ticket = findById(id);
        ticketRepository.deleteById(ticket.getId());
    }
}
