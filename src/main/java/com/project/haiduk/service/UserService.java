package com.project.haiduk.service;

import com.project.haiduk.domain.User;
import com.project.haiduk.dto.UserDto;

import java.util.List;

public interface UserService {
    User getCurrentUser(String email);

    List<UserDto> getAll();
}
