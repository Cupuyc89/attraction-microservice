package ru.pet_projects.attraction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.pet_projects.attraction.entities.Excursion;
import ru.pet_projects.attraction.repository.ExcursionRepository;

import java.util.List;

@RestController
public class ExcursionController {

    private final ExcursionRepository excursionRepository;

    @Autowired
    public ExcursionController(ExcursionRepository excursionRepository) {
        this.excursionRepository = excursionRepository;
    }

    @GetMapping("/excursion")
    public List<Excursion> getAll(){
        return excursionRepository.findAll();
    }

    @GetMapping("/excursion/{id}")
    public Excursion getById(@PathVariable Long id){
        return excursionRepository
                .findById(id)
                .orElseThrow();
    }
}
