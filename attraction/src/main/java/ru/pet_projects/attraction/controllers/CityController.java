package ru.pet_projects.attraction.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pet_projects.attraction.entities.City;
import ru.pet_projects.attraction.repository.CityRepository;

import java.util.List;

@RestController
public class CityController {

    private CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/city")
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @GetMapping("/city/{id}")
    public City getById(@PathVariable Long id) {
        return cityRepository
                .findById(id)
                .orElseThrow(()-> new EntityNotFoundException("City not found"));
    }
}