package ru.pet_projects.attraction.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cities")
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //@Min(12000)
    @Column(name = "population", nullable = false)
    private Integer population;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<Attraction> attractionList;

    @Column(name = "subway")
    private boolean hasSubway;

    public City() {
    }

    public City(Long id, String name, Integer population, List<Attraction> attractionList, boolean hasSubway) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.attractionList = attractionList;
        this.hasSubway = hasSubway;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    public boolean isHasSubway() {
        return hasSubway;
    }

    public void setHasSubway(boolean hasSubway) {
        this.hasSubway = hasSubway;
    }
}
