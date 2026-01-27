package ru.pet_projects.ticket_service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long excursionId;

    private Integer price;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_start")
    private LocalDate dateOfStartExcursion;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_end")
    private LocalDate dateOfEndExcursion;

    @Enumerated(EnumType.STRING)
    private Booking booking;

    public Ticket() {
    }

    public Ticket(Long id, Long excursionId, Integer price,
                   LocalDate dateOfStartExcursion,
                  LocalDate dateOfEndExcursion, Booking booking) {
        this.id = id;
        this.excursionId = excursionId;
        this.price = price;
        this.dateOfStartExcursion = dateOfStartExcursion;
        this.dateOfEndExcursion = dateOfEndExcursion;
        this.booking = booking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(Long excursionId) {
        this.excursionId = excursionId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getDateOfStartExcursion() {
        return dateOfStartExcursion;
    }

    public void setDateOfStartExcursion(LocalDate dateOfStartExcursion) {
        this.dateOfStartExcursion = dateOfStartExcursion;
    }

    public LocalDate getDateOfEndExcursion() {
        return dateOfEndExcursion;
    }

    public void setDateOfEndExcursion(LocalDate dateOfEndExcursion) {
        this.dateOfEndExcursion = dateOfEndExcursion;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}