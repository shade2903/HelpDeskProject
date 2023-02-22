package com.project.haiduk.converter;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.dto.TicketDto;

public class TicketConverter extends AbstractConverter<Ticket, TicketDto>{
    @Override
    Class<TicketDto> getDomainClass() {
        return TicketDto.class;
    }

    @Override
    Class<Ticket> getEntityClass() {
        return Ticket.class;
    }
}
