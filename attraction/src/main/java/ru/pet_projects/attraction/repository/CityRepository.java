package ru.pet_projects.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pet_projects.attraction.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
