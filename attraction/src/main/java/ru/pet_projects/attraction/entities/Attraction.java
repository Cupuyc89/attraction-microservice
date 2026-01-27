package ru.pet_projects.attraction.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="attractions")
public class Attraction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name = "kind")
    @Enumerated(EnumType.STRING)
    private KindOfAttraction kindOfAttraction;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ATTRACTIONS_EXCURSIONS",
            joinColumns = @JoinColumn(name = "ATTRACTION_ID",
                                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "EXCURSION_ID",
                                            referencedColumnName = "id"))
    private List<Excursion> excursionList;

    public Attraction() {
    }

    public Attraction(Long id, String name, LocalDate creationDate,
                      String description, KindOfAttraction kindOfAttraction,
                      City city, List<Excursion> excursionList) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.kindOfAttraction = kindOfAttraction;
        this.city = city;
        this.excursionList = excursionList;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KindOfAttraction getKindOfAttraction() {
        return kindOfAttraction;
    }

    public void setKindOfAttraction(KindOfAttraction kindOfAttraction) {
        this.kindOfAttraction = kindOfAttraction;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Excursion> getExcursionList() {
        return excursionList;
    }

    public void setExcursionList(List<Excursion> excursionList) {
        this.excursionList = excursionList;
    }
}