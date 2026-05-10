package ru.pet_projects.ticket_service.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.pet_projects.ticket_service.entities.Booking;

import java.time.LocalDate;

public record TicketDto(Long id,
                        @NotNull Long excursionId,
                        @NotNull @Positive int price,
                        @FutureOrPresent @NotNull LocalDate dateOfStartExcursion,
                        @Future @NotNull LocalDate dateOfEndExcursion,
                        Booking booking) {

}
