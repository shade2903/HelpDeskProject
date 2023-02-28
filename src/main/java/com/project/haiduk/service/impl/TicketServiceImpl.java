package com.project.haiduk.service.impl;

import com.project.haiduk.converter.TicketConverter;
import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.TicketDto;
import com.project.haiduk.exception.DataNotFoundException;
import com.project.haiduk.repository.TicketRepository;
import com.project.haiduk.service.TicketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger log = Logger.getLogger(TicketServiceImpl.class);
    private final TicketConverter ticketConverter;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketConverter ticketConverter, TicketRepository ticketRepository) {
        this.ticketConverter = ticketConverter;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    @Override
    public List<TicketDto> getAllTickets(Principal principal) {


       List<Ticket> ticketList = ticketRepository.getAllTickets();
       if(ticketList.isEmpty()){
           throw new DataNotFoundException(String.format("Tickets not found"));
       }
       List<TicketDto> ticketDtoList = new ArrayList<>();
       for(Ticket ticket : ticketList){
           ticketDtoList.add(ticketConverter.toDto(ticket));
       }
        log.info(String.format("Return all list ticket for %s ", principal.getName()));
        return ticketDtoList;
    }

    @Override
    public void createTicket(Principal principal, TicketDto ticketDto) {

    }

    @Override
    public Ticket getTicketById(Long id) {
        return null;
    }

    @Override
    public List<Ticket> getAllUserTicket(User user) {
        return null;
    }
}
