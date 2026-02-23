package ru.pet_projects.order_service.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.order_service.entities.Order;
import ru.pet_projects.order_service.repository.OrderRepository;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id){
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id){
        orderRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));
        orderRepository.deleteById(id);
    }
}