package com.project.haiduk.repository;

import com.project.haiduk.domain.Ticket;

import java.util.List;

public interface TicketRepository {
    List<Ticket> getAllTickets();
    List<Ticket> getOwnerTickets(Long ownerId);
    void saveTicket(Ticket ticket);
    Ticket getTicketById(Long id);
}
