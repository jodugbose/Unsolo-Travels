package com.interswitch.Unsolorockets.controllers;


import com.interswitch.Unsolorockets.dtos.requests.LoginDto;
import com.interswitch.Unsolorockets.dtos.requests.OTPRequest;
import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.requests.UserUpdateRequest;
import com.interswitch.Unsolorockets.dtos.responses.LoginResponse;
import com.interswitch.Unsolorockets.dtos.responses.UserProfileResponse;
import com.interswitch.Unsolorockets.exceptions.*;
import com.interswitch.Unsolorockets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserProfileResponse> signUp(@RequestBody UserDto userDto) throws UserException, IOException {
        UserProfileResponse response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginRequest) throws InvalidCredentialsException {
        String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponse response = new LoginResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyToken(@RequestBody OTPRequest otpRequest) throws UserNotFoundException {
        return new ResponseEntity<>(userService.verifyOTP(otpRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<UserProfileResponse> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest userUpdateRequest) throws UserNotFoundException, UserNotFoundException {
        UserProfileResponse response = userService.updateUserDetails(id, userUpdateRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
