package com.project.haiduk.controller;

import com.project.haiduk.converter.UserConverter;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.UserDto;
import com.project.haiduk.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@Transactional
public class UserController {
    private UserRepository userRepository;
    private UserConverter userConverter;

    public UserController(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<User> users = userRepository.getAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userConverter.toDtoList(users), HttpStatus.OK);
    }
}
