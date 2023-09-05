package com.interswitch.Unsolorockets.services.interfaces;

import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;

import java.io.IOException;

public interface UserService {
    SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException, IOException;
}
