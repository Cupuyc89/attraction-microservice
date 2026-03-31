package ru.pet_projects.ticket_service.dtos;

import ru.pet_projects.ticket_service.entities.Booking;

import java.time.LocalDate;

public record TicketDto(Long id, Long excursionId, int price,
                        LocalDate dateOfStartExcursion,
                        LocalDate dateOfEndExcursion, Booking booking) {

}
