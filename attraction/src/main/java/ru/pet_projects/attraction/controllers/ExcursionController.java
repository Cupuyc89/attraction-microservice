package ru.pet_projects.attraction.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/excursion")
    @ResponseStatus(HttpStatus.CREATED)
    public Excursion create(@RequestBody Excursion excursion){
        return excursionRepository.save(excursion);
    }

    @PutMapping("/excursion/{id}")
    public Excursion update(@PathVariable Long id, @RequestBody Excursion excursion){
        return excursionRepository
                .findById(id)
                .map(e -> {
                    e.setName(excursion.getName());
                    e.setDescription(excursion.getDescription());
                    e.setAttractionList(excursion.getAttractionList());
                    return excursionRepository.save(e);
                })
                .orElseThrow(()-> new EntityNotFoundException("Excursion not found"));
    }

    @DeleteMapping("/excursion/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id){
        excursionRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Excursion not found"));
        excursionRepository.deleteById(id);
    }
}
