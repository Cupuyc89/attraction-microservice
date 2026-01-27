package ru.pet_projects.payment_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.pet_projects.payment_service.entities.Card;

@RestController
public class CardController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/payment")
    public void payment(@RequestBody Card card){
        System.out.println("Created");
    }
}
