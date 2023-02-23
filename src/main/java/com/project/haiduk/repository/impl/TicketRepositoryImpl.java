package com.project.haiduk.repository.impl;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.repository.TicketRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private static final String GET_ALL_TICKETS = "from Ticket";
    private static final  String GET_EMPLOYEE_TICKETS = "from Ticket t where t.owner.id = :ownerId";
    private static final String GET_TICKET_BY_ID = "from Ticket t where t.id = :id";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Ticket> getAllTickets() {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_ALL_TICKETS);
        return query.list();
    }

    @Override
    public List<Ticket> getOwnerTickets(Long ownerId) {
        return null;
    }

    @Override
    public void saveTicket(Ticket ticket) {

    }

    @Override
    public Ticket getTicketById(Long id) {
        return null;
    }
}
