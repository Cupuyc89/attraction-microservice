package ru.pet_projects.order_service.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.order_service.entities.User;
import ru.pet_projects.order_service.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        return userRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("There is no user"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userRepository.save(user);
    }
}
