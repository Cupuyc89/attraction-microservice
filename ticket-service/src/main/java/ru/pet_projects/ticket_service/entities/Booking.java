package ru.pet_projects.ticket_service.entities;

/** This service organizes access to attractions.
 * There's an info about tickets.
 * There's an opportunity to book, to cansel the booking, to change tickets
 * and to refuse to the ticket.
 * This service connects with the order service. */

public enum Booking {

    BOOKED("The ticket is booked"),
    NOT_BOOKED("The ticket isn't booked");

    private final String status;

    Booking(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}