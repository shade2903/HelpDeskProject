package com.project.haiduk.repository;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.dto.TicketDto;

import java.util.List;

public interface TicketRepository {
    List<Ticket> getAllTickets();
    List<Ticket> getOwnerTickets(Long ownerId);
    List<Ticket> getNewEmployeesTickets(Long userId);
    List<Ticket> getApproverTickets(Long approverId);
    List<Ticket> getApprovedTickets();
    List<Ticket> getAssigneeTickets(Long assigneeId);

    void saveTicket(Ticket ticket);
    Ticket getTicketById(Long id);
}
