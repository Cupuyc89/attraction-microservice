package ru.pet_projects.ticket_service.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pet_projects.ticket_service.dtos.TicketDto;
import ru.pet_projects.ticket_service.entities.Ticket;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER)
public interface TicketMapper {

    TicketDto toDto(Ticket ticket);
    Ticket toEntity(TicketDto ticketDto);
}
