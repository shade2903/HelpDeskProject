package com.project.haiduk.service.impl;

import com.project.haiduk.domain.User;


import com.project.haiduk.security.CustomUserDetails;
import com.project.haiduk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public MyUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = userService.getCurrentUser(email);
       if(user == null){
           throw new UsernameNotFoundException(String.format("User with email: %s is not found!", email));
       }
        return new CustomUserDetails(user);
    }
}
