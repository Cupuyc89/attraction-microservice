package ru.pet_projects.payment_service.controllers;

import org.springframework.web.bind.annotation.*;
import ru.pet_projects.payment_service.dtos.PaymentRequestDto;
import ru.pet_projects.payment_service.dtos.PaymentResponseDto;
import ru.pet_projects.payment_service.services.PaymentService;

@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public PaymentResponseDto pay(@RequestBody PaymentRequestDto requestDto){
        return paymentService.pay(requestDto);
    }

    @GetMapping("/refund/{id}")
    public PaymentResponseDto refund(@PathVariable("id") Long orderId){
        return paymentService.refund(orderId);
    }
}
