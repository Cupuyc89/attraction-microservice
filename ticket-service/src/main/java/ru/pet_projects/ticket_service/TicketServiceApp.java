package ru.pet_projects.ticket_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/** This service organizes access to attractions.
 * There's an info about tickets.
 * There's an opportunity to book, to cancel the booking, to change tickets
 * and to refuse to the ticket.
 * This service connects with the order service. */

@EnableDiscoveryClient
@SpringBootApplication
public class TicketServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApp.class, args);
    }
}
