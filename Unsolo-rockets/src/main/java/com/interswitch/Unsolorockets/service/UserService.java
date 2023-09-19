package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.OTPRequest;
import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.requests.UserUpdateRequest;
import com.interswitch.Unsolorockets.dtos.responses.UserProfileResponse;

import com.interswitch.Unsolorockets.exceptions.UserException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;

import java.io.IOException;

public interface UserService {
    UserProfileResponse createUser(UserDto userDto) throws UserException, IOException;
    String authenticateUser(String email, String password);
    String verifyOTP(OTPRequest otpRequest) throws UserNotFoundException;
    UserProfileResponse updateUserDetails(long id, UserUpdateRequest userUpdateRequest) throws UserNotFoundException;
}
