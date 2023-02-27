package com.project.haiduk.controller;

import com.project.haiduk.dto.AuthenticationDto;
import com.project.haiduk.security.JWTUtil;
import com.project.haiduk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthenticationController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationController(JWTUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> performLogin(@RequestBody AuthenticationDto authenticationDto, Principal principal){

        UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(authenticationDto.getUserName(),
                authenticationDto.getPassword());
        Map<String, String> responseMap = new HashMap<>();
        try{
            authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            responseMap.put("message", "Incorrect credentials!");
            return responseMap;
        }
        String token = jwtUtil.generateToken(authenticationDto.getUserName());
        responseMap.put("jwt-token", token);
        return responseMap;
    }

}
