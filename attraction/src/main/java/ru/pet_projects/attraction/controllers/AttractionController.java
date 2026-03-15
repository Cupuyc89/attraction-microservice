package ru.pet_projects.attraction.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.attraction.entities.Attraction;
import ru.pet_projects.attraction.repository.AttractionRepository;

import java.util.List;

@RestController
public class AttractionController {

    private final AttractionRepository attractionRepository;

    @Autowired
    public AttractionController(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @GetMapping("/attraction")
    public List<Attraction> getAll() {
        return attractionRepository.findAll();
    }

    @GetMapping("/attraction/{id}")
    public Attraction getById(@PathVariable Long id) {
        return attractionRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attraction not found"));
    }

    @PostMapping("/attraction")
    @ResponseStatus(HttpStatus.CREATED)
    public Attraction create(@RequestBody Attraction attraction) {
        return attractionRepository.save(attraction);
    }
}