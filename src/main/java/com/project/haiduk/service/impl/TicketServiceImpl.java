package com.project.haiduk.service.impl;

import com.project.haiduk.converter.TicketConverter;
import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.User;
import com.project.haiduk.domain.enums.Role;
import com.project.haiduk.dto.TicketDto;
import com.project.haiduk.exception.DataNotFoundException;
import com.project.haiduk.repository.TicketRepository;
import com.project.haiduk.repository.UserRepository;
import com.project.haiduk.service.TicketService;
import com.project.haiduk.utill.TicketValidator;
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
    private final UserRepository userRepository;
    private final TicketValidator validator;

    @Autowired
    public TicketServiceImpl(TicketConverter ticketConverter, TicketRepository ticketRepository,
                             UserRepository userRepository, TicketValidator validator) {
        this.ticketConverter = ticketConverter;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Transactional
    @Override
    public List<TicketDto> getAllTickets(Principal principal) {
        List<Ticket> ticketList = ticketRepository.getAllTickets();
        if (ticketList.isEmpty()) {
            throw new DataNotFoundException(String.format("Tickets not found"));
        }
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDtoList.add(ticketConverter.toDto(ticket));
        }
        return ticketDtoList;
    }

    @Transactional
    @Override
    public void createTicket(Principal principal, TicketDto ticketDto) {
        log.info(String.format("Return all list ticket for %s ", principal.getName()));

    }

    @Transactional
    @Override
    public TicketDto getTicketById(Long id, Principal principal) {
        User user = userRepository.getByEmail(principal.getName());
        Ticket ticket = ticketRepository.getTicketById(id);
        validator.validateAccess(user, ticket);
        if(ticket == null){
            throw new DataNotFoundException(String.format("Ticket with id %d not found!", id));
        }
        return ticketConverter.toDto(ticket);
    }

    @Transactional
    @Override
    public List<TicketDto> getAllUserTickets(Principal principal) {
        User user = userRepository.getByEmail(principal.getName());
        List<TicketDto> ticketDto = new ArrayList<>();
        if (user.getRole().equals(Role.EMPLOYEE)) {
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getOwnerTickets(user.getId())));
        } else if (user.getRole().equals(Role.MANAGER)) {
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getOwnerTickets(user.getId())));
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getNewEmployeesTickets(user.getId())));
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getApproverTickets(user.getId())));
        } else {
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getAssigneeTickets(user.getId())));
            ticketDto.addAll(ticketConverter.toDtoList(ticketRepository.getApprovedTickets()));
        }
        return ticketDto;
    }
}
