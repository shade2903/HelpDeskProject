package com.project.haiduk.controller;

import com.project.haiduk.converter.UserConverter;
import com.project.haiduk.domain.User;
import com.project.haiduk.dto.UserDto;
import com.project.haiduk.repository.UserRepository;
import com.project.haiduk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;
    private UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> users = userService.getAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
