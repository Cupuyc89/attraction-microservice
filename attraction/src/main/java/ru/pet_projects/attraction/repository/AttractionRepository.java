package ru.pet_projects.attraction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet_projects.attraction.entities.Attraction;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
