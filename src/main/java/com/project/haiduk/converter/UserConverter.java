package com.project.haiduk.converter;

import com.project.haiduk.domain.User;
import com.project.haiduk.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractConverter<User, UserDto> {
    @Override
    Class<UserDto> getDomainClass() {
        return UserDto.class;
    }

    @Override
    Class<User> getEntityClass() {
        return User.class;
    }
}
