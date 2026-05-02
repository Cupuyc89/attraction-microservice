package ru.pet_projects.ticket_service.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,
        String detailedMessage,
        LocalDateTime  timestamp) {
}
