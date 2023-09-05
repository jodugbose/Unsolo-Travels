package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;

public interface UserService {
    SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException;
    String authenticateUser(String email, String password);
}
