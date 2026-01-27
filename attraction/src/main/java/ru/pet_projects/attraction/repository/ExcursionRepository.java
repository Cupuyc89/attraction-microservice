package ru.pet_projects.attraction.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pet_projects.attraction.entities.Excursion;

@Repository
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
}
