package com.project.haiduk.controller;

import com.project.haiduk.dto.AuthenticationDto;
import com.project.haiduk.security.JWTUtil;
import com.project.haiduk.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AuthenticationController {
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public AuthenticationController(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> performLogin(@RequestBody  @Valid AuthenticationDto authenticationDto, BindingResult bindingResult){
        Map<String, String> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("message", bindingResult.toString());
            return responseMap;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(authenticationDto.getUserName(),
                authenticationDto.getPassword());


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
