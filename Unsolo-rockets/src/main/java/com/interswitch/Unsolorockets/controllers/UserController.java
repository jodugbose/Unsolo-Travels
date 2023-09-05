package com.interswitch.Unsolorockets.controllers;


import com.interswitch.Unsolorockets.dtos.requests.LoginDto;
import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.LoginResponse;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.InvalidCredentialsException;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException, IOException {
        SignUpResponse response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginRequest) throws InvalidCredentialsException {
        String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
