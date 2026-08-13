package ru.pet_projects.payment_service.dtos;

import ru.pet_projects.payment_service.entities.PaymentStatus;

public record PaymentResponseDto(Long id,
                                 PaymentStatus status) {
}
