package com.project.haiduk.utill;

import com.project.haiduk.domain.Ticket;
import com.project.haiduk.domain.User;
import com.project.haiduk.domain.enums.Role;
import com.project.haiduk.domain.enums.State;
import com.project.haiduk.exception.ForbiddenResourceException;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator {
    public void validateAccess(User user, Ticket ticket) {
        if(user.getRole().equals(Role.EMPLOYEE)){
            validateEmployeeAccess(user, ticket);
        }else if (user.getRole().equals(Role.MANAGER)){
            validateManagerAccess(user, ticket);
        }else if (user.getRole().equals(Role.MANAGER)){
            validateEngineerAccess(user, ticket);
        }
    }

    private void validateEmployeeAccess(User user, Ticket ticket) {
        if (!user.getId().equals(ticket.getOwner().getId()))
            throw new ForbiddenResourceException(exceptionAccessMsg(user));
    }

    private void validateEngineerAccess(User user, Ticket ticket) {
        if (!((ticket.getOwner().equals(Role.EMPLOYEE) || ticket.getOwner().equals(Role.MANAGER)) && (ticket.getState().equals(State.APPROVED))) || !(ticket.getAssignee().getId().equals(user.getId()) &&
                (ticket.getState().equals(State.IN_PROGRESS) || ticket.getState().equals(State.DONE))))
            throw new ForbiddenResourceException(exceptionAccessMsg(user));
    }

    private void validateManagerAccess(User user, Ticket ticket) {
        validateEmployeeAccess(user, ticket);
        if (!(ticket.getOwner().getRole().equals(Role.EMPLOYEE) && ticket.getState().equals(State.NEW)) ||
                !((ticket.getApprover().getId().equals(user.getId())) && ((ticket.getState().equals(State.APPROVED)) ||
                        (ticket.getState().equals(State.DECLINED)) || (ticket.getState().equals(State.CANCELED)) ||
                        (ticket.getState().equals(State.IN_PROGRESS)) || (ticket.getState().equals(State.DONE)))))
            throw new ForbiddenResourceException(exceptionAccessMsg(user));
    }

    private String exceptionAccessMsg(User user) {
        return String.format("no access to resources for user: %s !", user.getEmail());
    }

}
