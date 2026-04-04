package ru.pet_projects.ticket_service.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Booking;
import ru.pet_projects.ticket_service.entities.Ticket;
import ru.pet_projects.ticket_service.mappers.TicketMapper;
import ru.pet_projects.ticket_service.services.TicketService;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TicketMapper mapper;

    @MockitoBean
    private TicketService ticketService;

    @Test
    void getByIdTest() throws Exception{
        TicketDto ticket =  new TicketDto(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(mapper.toDto(ticketService.findById(1L))).thenReturn(ticket);

        mockMvc.perform(get("/ticket/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.excursionId").value(1))
                .andExpect(jsonPath("$.dateOfStartExcursion").value("2026-08-20"))
                .andExpect(jsonPath("$.dateOfEndExcursion").value("2026-08-30"))
                .andExpect(jsonPath("$.booking").value("BOOKED"));
    }

    @Test
    void createTest() throws Exception{
        Ticket ticket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketService.save(mapper.toEntity(any(TicketDto.class))))
                .thenReturn(ticket);

        mockMvc.perform(post("/ticket/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"null\",\"excursionId\":\"1\"," +
                                "\"price\":\"1234\"}" +
                                "\"dateOfStartExcursion\":\"2026-08-20\"," +
                                "\"dateOfEndExcursion\":\"2026-08-30\"," +
                                "\"booking\":\"BOOKED\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.excursionId").value(1))
                .andExpect(jsonPath("$.price").value(1234))
                .andExpect(jsonPath("$.dateOfStartExcursion").value("2026-08-20"))
                .andExpect(jsonPath("$.dateOfEndExcursion").value("2026-08-30"))
                .andExpect(jsonPath("$.booking").value("BOOKED"));
    }

    @Test
    void updateTest() throws Exception{
        Ticket ticket =  new Ticket(1L, 1L, 1234,
                LocalDate.of(2026,8,20),
                LocalDate.of(2026,8,30),
                Booking.BOOKED);
        when(ticketService.update(eq(mapper.toEntity(any(TicketDto.class))),anyLong()))
                .thenReturn(ticket);

        mockMvc.perform(put("/ticket/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"excursionId\":\"1\"," +
                                "\"price\":\"1234\"," +
                                "\"dateOfStartExcursion\":\"2026-08-20\"," +
                                "\"dateOfEndExcursion\":\"2026-08-30\"," +
                                "\"booking\":\"BOOKED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.excursionId").value(1))
                .andExpect(jsonPath("$.price").value(1234))
                .andExpect(jsonPath("$.dateOfStartExcursion").value("2026-08-20"))
                .andExpect(jsonPath("$.dateOfEndExcursion").value("2026-08-30"))
                .andExpect(jsonPath("$.booking").value("BOOKED"));
    }

    @Test
    void deleteTest() throws Exception{
        Long id = 1L;
        doNothing().when(ticketService).deleteById(id);

        mockMvc.perform(delete("/ticket/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(ticketService).deleteById(id);
    }
}
