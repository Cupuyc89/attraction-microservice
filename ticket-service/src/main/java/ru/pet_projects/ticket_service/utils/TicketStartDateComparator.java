package ru.pet_projects.ticket_service.utils;

import ru.pet_projects.ticket_service.entities.Ticket;

import java.util.Comparator;

public class TicketStartDateComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getDateOfStartExcursion().compareTo(o2.getDateOfStartExcursion());
    }
}
