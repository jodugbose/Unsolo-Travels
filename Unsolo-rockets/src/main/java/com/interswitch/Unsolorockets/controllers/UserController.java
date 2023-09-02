package com.interswitch.Unsolorockets.controllers;


import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException {
            var response = userService.createUser(userDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
