package ru.pet_projects.ticket_service.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.repository.TicketRepository;

import java.time.LocalDate;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketRepository ticketRepository;

    @Test
    void getByIdTest() throws Exception{
        Ticket ticket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        mockMvc.perform(get("/ticket/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.excursionId").value(1))
                .andExpect(jsonPath("$.dateOfStartExcursion").value("2026-08-20"))
                .andExpect(jsonPath("$.dateOfEndExcursion").value("2026-08-30"))
                .andExpect(jsonPath("$.booking").value("BOOKED"));
    }
}
