package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.OTPRequest;
import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.requests.UserUpdateRequest;
import com.interswitch.Unsolorockets.dtos.responses.UserProfileResponse;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;

import java.io.IOException;

public interface UserService {
    UserProfileResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException, IOException;
    String authenticateUser(String email, String password);
    String verifyOTP(OTPRequest otpRequest);
    UserProfileResponse updateUserDetails(long id, UserUpdateRequest userUpdateRequest) throws UserNotFoundException;
}
