package ru.pet_projects.payment_service.services;

import ru.pet_projects.payment_service.dtos.PaymentRequestDto;
import ru.pet_projects.payment_service.dtos.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto pay(PaymentRequestDto requestDto);
    PaymentResponseDto refund(Long id);

}
