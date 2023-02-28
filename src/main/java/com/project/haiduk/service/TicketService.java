package com.project.haiduk.service;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.TicketDto;

import java.security.Principal;
import java.util.List;

public interface TicketService {
    List<TicketDto> getAllTickets(Principal principal);
    void createTicket(Principal principal, TicketDto ticketDto);
    Ticket getTicketById(Long id);
    List<Ticket> getAllUserTicket(User user);
}
