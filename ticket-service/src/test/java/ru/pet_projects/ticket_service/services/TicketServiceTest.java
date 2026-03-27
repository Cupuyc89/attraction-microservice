package ru.pet_projects.ticket_service.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.repository.TicketRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(TicketServiceImp.class)
class TicketServiceTest{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketRepository ticketRepository;

    @Test
    void getByIdTest(){
        Ticket ticket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        Ticket newTicket = ticketRepository.findById(1L).orElseThrow();

        assertEquals(newTicket.getId(),ticket.getId());
        assertEquals(newTicket.getExcursionId(),ticket.getExcursionId());
        assertEquals(newTicket.getPrice(),ticket.getPrice());
        assertEquals(newTicket.getDateOfStartExcursion(),ticket.getDateOfStartExcursion());
        assertEquals(newTicket.getDateOfEndExcursion(),ticket.getDateOfEndExcursion());
        assertEquals(newTicket.getBooking(),ticket.getBooking());
    }

    @Test
    void createTest(){
        Ticket ticket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket ticketNew =  new Ticket(null, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        Ticket newTicket = ticketRepository.save(ticketNew);

        assertEquals(newTicket.getId(),ticket.getId());
        assertEquals(newTicket.getExcursionId(),ticket.getExcursionId());
        assertEquals(newTicket.getPrice(),ticket.getPrice());
        assertEquals(newTicket.getDateOfStartExcursion(),ticket.getDateOfStartExcursion());
        assertEquals(newTicket.getDateOfEndExcursion(),ticket.getDateOfEndExcursion());
        assertEquals(newTicket.getBooking(),ticket.getBooking());
    }

    @Test
    void updateTest(){
        Ticket newTicket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(newTicket);
        Ticket updatedTicket = ticketRepository.save(newTicket);

        assertEquals(newTicket.getExcursionId(), updatedTicket.getExcursionId());
    }

    @Test
    void deleteTest(){
        Long id = 1L;
        ticketRepository.deleteById(id);

        verify(ticketRepository).deleteById(id);
    }
}
