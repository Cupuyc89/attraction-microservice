package ru.pet_projects.ticket_service.mappers;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicketMapperTest {

    private final TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void toDtoTest(){
        Ticket ticket = new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);

        TicketDto ticketDto = ticketMapper.toDto(ticket);

        assertEquals(ticketDto.id(), ticket.getId());
        assertEquals(ticketDto.excursionId(), ticket.getExcursionId());
        assertEquals(ticketDto.price(), ticket.getPrice());
        assertEquals(ticketDto.dateOfStartExcursion(), ticket.getDateOfStartExcursion());
        assertEquals(ticketDto.dateOfEndExcursion(), ticket.getDateOfEndExcursion());
        assertEquals(ticketDto.booking(), ticket.getBooking());
    }

    @Test
    void toEntityTest(){
        TicketDto ticketDto = new TicketDto(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);

        Ticket ticket = ticketMapper.toEntity(ticketDto);

        assertEquals(ticketDto.id(), ticket.getId());
        assertEquals(ticketDto.excursionId(), ticket.getExcursionId());
        assertEquals(ticketDto.price(), ticket.getPrice());
        assertEquals(ticketDto.dateOfStartExcursion(), ticket.getDateOfStartExcursion());
        assertEquals(ticketDto.dateOfEndExcursion(), ticket.getDateOfEndExcursion());
        assertEquals(ticketDto.booking(), ticket.getBooking());
    }
}