package ru.pet_projects.payment_service.services;

import ru.pet_projects.payment_service.dtos.PaymentRequestDto;
import ru.pet_projects.payment_service.dtos.PaymentResponseDto;
import ru.pet_projects.payment_service.entities.Currency;
import ru.pet_projects.payment_service.entities.Payment;
import ru.pet_projects.payment_service.entities.PaymentStatus;
import ru.pet_projects.payment_service.repository.PaymentRepository;

import java.time.LocalDateTime;


public class PaymentServiceImp implements PaymentService{

    private final PaymentRepository paymentRepository;

    public PaymentServiceImp(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public PaymentResponseDto pay(PaymentRequestDto requestDto) {
        Payment payment = new Payment(null, requestDto.orderId(), requestDto.amount(),
                Currency.RUB, PaymentStatus.PENDING, LocalDateTime.now());
        payment = paymentRepository.save(payment);

        if (Math.random() < 0.1) {
            payment.setStatus(PaymentStatus.FAILED);
            payment = paymentRepository.save(payment);
            return new PaymentResponseDto(payment.getId(), PaymentStatus.FAILED);
        }

        payment.setStatus(PaymentStatus.COMPLETED);
        payment = paymentRepository.save(payment);
        return new PaymentResponseDto(payment.getId(), PaymentStatus.COMPLETED);
    }

    @Override
    public PaymentResponseDto refund(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);

        if (Math.random() < 0.1)
            return new PaymentResponseDto(payment.getId(), PaymentStatus.FAILED);

        payment.setStatus(PaymentStatus.REFUNDED);
        payment = paymentRepository.save(payment);
        return new PaymentResponseDto(payment.getId(), PaymentStatus.REFUNDED);
    }
}
