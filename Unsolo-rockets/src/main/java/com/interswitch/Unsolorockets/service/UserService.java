package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.OTPRequest;
import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;

import java.io.IOException;

public interface UserService {
    SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException, IOException;
    String authenticateUser(String email, String password);
    String verifyOTP(OTPRequest otpRequest);
}
