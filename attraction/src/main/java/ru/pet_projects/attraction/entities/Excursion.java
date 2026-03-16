package ru.pet_projects.attraction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="excursions")
public class Excursion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ATTRACTIONS_EXCURSIONS",
            joinColumns = @JoinColumn(name = "EXCURSION_ID",
                                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ATTRACTION_ID",
                                            referencedColumnName = "id"))
    private List<Attraction> attractionList;

    public Excursion() {
    }

    public Excursion(Long id, String name, String description, List<Attraction> attractionList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attractionList = attractionList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(List<Attraction> attractionList) {
        this.attractionList = attractionList;
    }
}
