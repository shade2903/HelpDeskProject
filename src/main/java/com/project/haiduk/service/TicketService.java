package com.project.haiduk.service;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.TicketDto;

import java.security.Principal;
import java.util.List;

public interface TicketService {
    List<TicketDto> getAllTickets(Principal principal);
    void createTicket(Principal principal, TicketDto ticketDto);
    TicketDto getTicketById(Long id);
    List<TicketDto> getAllUserTickets(Principal principal);
}
