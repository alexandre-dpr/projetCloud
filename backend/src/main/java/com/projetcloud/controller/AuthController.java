package com.projetcloud.controller;

import com.projetcloud.dto.request.UserDto;
import com.projetcloud.exceptions.BadLoginException;
import com.projetcloud.exceptions.LoginAlreadyUsedException;
import com.projetcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("{api.version}/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "{api.version}/login")
    public ResponseEntity<String> login(@RequestBody UserDto req) throws BadLoginException {
        return ResponseEntity.ok(userService.login(req));
    }

    @PostMapping(value = "{api.version}/register")
    public ResponseEntity<String> register(@RequestBody UserDto req) throws LoginAlreadyUsedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(req));
    }
}
