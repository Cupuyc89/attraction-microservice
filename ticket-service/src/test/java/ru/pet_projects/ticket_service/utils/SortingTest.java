package ru.pet_projects.ticket_service.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

class SortingTest {

    private List<Ticket> ticketList;

    private static final Long ID_1 = 1L;
    private static final Long ID_2 = 2L;
    private static final Long ID_3 = 3L;

    @BeforeEach
    void init(){
        ticketList = new ArrayList<>();
        ticketList.add(new Ticket(1L, 1L, 3000,
                LocalDate.of(2026,7,20),
                LocalDate.of(2026,7,30),
                Booking.BOOKED));
        ticketList.add(new Ticket(2L, 1L, 1000,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED));
        ticketList.add(new Ticket(3L, 1L, 2000,
                LocalDate.of(2025,8,21),
                LocalDate.of(2025,8,30),
                Booking.BOOKED));
    }

    @Test
    void sortByPriceTest(){
        assertEquals(ID_1, ticketList.get(0).getId());

        ticketList.sort(TicketComparator.getComparator("price"));

        assertEquals(ID_2, ticketList.get(0).getId());
        assertEquals(ID_3, ticketList.get(1).getId());
        assertEquals(ID_1, ticketList.get(2).getId());
    }

    @Test
    void sortByStartDateTest(){
        assertEquals(ID_1, ticketList.get(0).getId());

        ticketList.sort(TicketComparator.getComparator(""));

        assertEquals(ID_3, ticketList.get(0).getId());
        assertEquals(ID_1, ticketList.get(1).getId());
        assertEquals(ID_2, ticketList.get(2).getId());
    }
    @Test
    void sortByEndDateTest(){
        assertEquals(ID_1, ticketList.get(0).getId());

        ticketList.sort(TicketComparator.getComparator("endDate"));

        assertEquals(ID_3, ticketList.get(0).getId());
        assertEquals(ID_1, ticketList.get(1).getId());
        assertEquals(ID_2, ticketList.get(2).getId());
    }
}
