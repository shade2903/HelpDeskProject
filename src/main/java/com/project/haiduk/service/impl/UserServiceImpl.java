package com.project.haiduk.service.impl;

import com.project.haiduk.converter.UserConverter;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.UserDto;
import com.project.haiduk.exception.DataNotFoundException;
import com.project.haiduk.exception.UserNotFoundException;
import com.project.haiduk.repository.UserRepository;
import com.project.haiduk.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserConverter userConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }


    @Transactional
    @Override
    public User getCurrentUser(String email) {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format("User's email:%s not found!"));
        }
        return user;
    }

    @Transactional
    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("List of users is not found!");
        }
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(userConverter.toDto(user));
        }
        return usersDto;
    }
}
