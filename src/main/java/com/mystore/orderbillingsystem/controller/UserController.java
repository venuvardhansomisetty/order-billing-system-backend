package com.mystore.orderbillingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.orderbillingsystem.dto.LoginRequest;
import com.mystore.orderbillingsystem.entity.User;
import com.mystore.orderbillingsystem.repository.UserRepository;
import com.mystore.orderbillingsystem.service.JwtService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(
            @RequestBody User user) {

        user.setPassword(
        passwordEncoder.encode(
                user.getPassword()));

return userRepository.save(user);
    }

    @PostMapping("/login")
public String login(
        @RequestBody LoginRequest request) {

    User user = userRepository
            .findByUsername(request.getUsername())
            .orElseThrow();

    if (!passwordEncoder.matches(
        request.getPassword(),
        user.getPassword())) {

    throw new RuntimeException("Invalid password");
}

    return jwtService.generateToken(
        user.getUsername(),
        user.getRole());
}


@Autowired
private JwtService jwtService;

@Autowired
private PasswordEncoder passwordEncoder;
}