package com.project.haiduk.converter;

import com.project.haiduk.dto.UserDto;
import org.h2.engine.User;

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
