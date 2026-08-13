package ru.pet_projects.payment_service.dtos;

import java.math.BigDecimal;

public record PaymentRequestDto(Long userId,
                                Long orderId,
                                BigDecimal amount) {
}
