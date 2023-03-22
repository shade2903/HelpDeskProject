package com.project.haiduk.repository;

import com.project.haiduk.domain.User;

import java.util.List;

public interface UserRepository {
    User getByEmail(String email);

    List<User> getAll();
}
