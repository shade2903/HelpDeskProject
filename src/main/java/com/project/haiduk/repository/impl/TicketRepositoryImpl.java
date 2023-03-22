package com.project.haiduk.repository.impl;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.enums.Role;
import com.project.haiduk.domain.enums.State;
import com.project.haiduk.repository.TicketRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class TicketRepositoryImpl implements TicketRepository {
    private static final String GET_ALL_TICKETS = "from Ticket";
    private static final String GET_EMPLOYEE_TICKETS = "from Ticket t where t.owner.id = :ownerId";
    private static final String GET_NEW_EMPLOYEE_TICKETS = "from Ticket t where (t.owner.role = :role and t.state = :state) or " +
            "(t.owner.role = :roleManager and t.state = :state and t.owner.id != :id)";
    private static final String GET_APPROVED_TICKETS = "from Ticket t where t.state =: state";
    private static final String GET_APPROVER_TICKETS = "from Ticket t where t.approver.id = :approverId and " +
            "(t.state = : approved or  t.state = : declined or  t.state = : cancelled or  t.state = : inProgress or  t.state = : done)";
    private static final String GET_ASSIGNEE_TICKETS = "from Ticket t where t.assignee.id = :assigneeId and " +
            "(t.state = :InProgress or t.state = :done)";
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
        Query query = sessionFactory.getCurrentSession().createQuery(GET_EMPLOYEE_TICKETS);
        query.setParameter("ownerId", ownerId);
        return query.list();
    }

    @Override
    public List<Ticket> getNewEmployeesTickets(Long userId) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_NEW_EMPLOYEE_TICKETS);
        query.setParameter("role", Role.EMPLOYEE);
        query.setParameter("state", State.NEW);
        query.setParameter("roleManager", Role.MANAGER);
        query.setParameter("id", userId);
        return query.list();
    }

    @Override
    public List<Ticket> getApproverTickets(Long approverId) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_APPROVER_TICKETS);
        query.setParameter("approverId", approverId);
        query.setParameter("approved", State.APPROVED);
        query.setParameter("declined", State.DECLINED);
        query.setParameter("cancelled", State.CANCELED);
        query.setParameter("inProgress", State.IN_PROGRESS);
        query.setParameter("done", State.DONE);
        return query.list();
    }

    @Override
    public List<Ticket> getApprovedTickets() {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_APPROVED_TICKETS);
        query.setParameter("state", State.APPROVED);
        return query.list();
    }

    @Override
    public List<Ticket> getAssigneeTickets(Long assigneeId) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_ASSIGNEE_TICKETS);
        query.setParameter("assigneeId", assigneeId);
        query.setParameter("InProgress", State.IN_PROGRESS);
        query.setParameter("done", State.DONE);
        return query.list();

    }

    @Override
    public void saveTicket(Ticket ticket) {
        sessionFactory.getCurrentSession().save(ticket);

    }

    @Override
    public Ticket getTicketById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(GET_TICKET_BY_ID);
        query.setParameter("id", id);
        return (Ticket) query.uniqueResult();
    }
}
