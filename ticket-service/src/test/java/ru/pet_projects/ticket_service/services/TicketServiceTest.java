package ru.pet_projects.ticket_service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.mappers.TicketMapper;
import ru.pet_projects.ticket_service.repository.TicketRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest{

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketServiceImp ticketService;

    private Ticket ticket;
    private TicketDto ticketDto;

    @BeforeEach
    void initTickets(){
        ticket = new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);

        ticketDto = new TicketDto(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
    }

    @Test
    void getByIdTest(){
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(ticketDto);

        TicketDto newTicket = ticketService.findById(1L);

        assertEquals(newTicket.id(),ticket.getId());
        assertEquals(newTicket.excursionId(),ticket.getExcursionId());
        assertEquals(newTicket.price(),ticket.getPrice());
        assertEquals(newTicket.dateOfStartExcursion(),ticket.getDateOfStartExcursion());
        assertEquals(newTicket.dateOfEndExcursion(),ticket.getDateOfEndExcursion());
        assertEquals(newTicket.booking(),ticket.getBooking());
    }

    @Test
    void createTest(){
        TicketDto responseTicket =  new TicketDto(null, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);

        when(ticketMapper.toEntity(any(TicketDto.class))).thenReturn(ticket);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(ticketDto);

        TicketDto newTicket = ticketService.save(responseTicket);

        assertEquals(newTicket.id(),ticket.getId());
        assertEquals(newTicket.excursionId(),ticket.getExcursionId());
        assertEquals(newTicket.price(),ticket.getPrice());
        assertEquals(newTicket.dateOfStartExcursion(),ticket.getDateOfStartExcursion());
        assertEquals(newTicket.dateOfEndExcursion(),ticket.getDateOfEndExcursion());
        assertEquals(newTicket.booking(),ticket.getBooking());
    }

    @Test
    void updateTest(){
        TicketDto responseTicket =  new TicketDto(1L, 2L, 4321,
                LocalDate.of(2026,8,21),
                LocalDate.of(2026,8,31),
                Booking.BOOKED);

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(responseTicket);

        TicketDto updatedTicket = ticketService.update(responseTicket, responseTicket.id());

        assertEquals(responseTicket.excursionId(), updatedTicket.excursionId());
        assertEquals(responseTicket.price(), updatedTicket.price());
        assertEquals(responseTicket.dateOfStartExcursion(), updatedTicket.dateOfStartExcursion());
        assertEquals(responseTicket.dateOfEndExcursion(), updatedTicket.dateOfEndExcursion());
    }

    @Test
    void deleteTest(){
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketMapper.toDto(any(Ticket.class))).thenReturn(ticketDto);

        TicketDto deletingTicket = ticketService.findById(1L);

        ticketService.deleteById(deletingTicket.id());

        verify(ticketRepository).deleteById(deletingTicket.id());
    }
}
