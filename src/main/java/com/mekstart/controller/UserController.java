package com.mekstart.controller;

import com.mekstart.dto.LoginRequest;
import com.mekstart.dto.RegisterRequest;
import com.mekstart.exception.ResourceNotFoundException;
import com.mekstart.security.JwtUtils;
import com.mekstart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @Valid @RequestBody RegisterRequest registerRequest) throws ResourceNotFoundException {
        userService.registerUser(registerRequest);
        String message = "Success";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                loginRequest.getPassword()));
        String token = jwtUtils.generateToken(authentication);
        return new ResponseEntity<>(token, HttpStatus.CREATED);

    }

}
