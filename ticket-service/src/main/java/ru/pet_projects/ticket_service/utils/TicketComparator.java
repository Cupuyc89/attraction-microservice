package ru.pet_projects.ticket_service.utils;

import ru.pet_projects.ticket_service.entities.Ticket;

import java.util.Comparator;

public interface TicketComparator {

    static Comparator<Ticket> getComparator(String sortingField) {

        if("price".equals(sortingField)) return new TicketPriceComparator();

        if("endDate".equals(sortingField)) return new TicketEndDateComparator();

        return new TicketStartDateComparator();
    }
}
