package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.InvalidCredentialsException;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.models.User;
import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.Role;
import com.interswitch.Unsolorockets.respository.UserRepository;
import com.interswitch.Unsolorockets.security.IPasswordEncoder;
import com.interswitch.Unsolorockets.service.UserService;
import com.interswitch.Unsolorockets.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  JwtTokenUtils jwtTokenUtils;

    private final IPasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException {
        checkIfUserExist(userDto.getEmail());
        confirmPasswords(userDto.getPassword(), userDto.getPassword2());

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = createUserFromDto(userDto, encodedPassword);


        return SignUpResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender().toString())
                .build();
    }

    private User createUserFromDto(UserDto userDto, String encodedPassword) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .dateOfBirth(userDto.getDateOfBirth())
                .email(userDto.getEmail())
                .gender(Gender.valueOf(userDto.getGender().toUpperCase()))
                .password(encodedPassword)
                .phoneNumber(userDto.getPhoneNumber())
                .build();

        assignRole(userDto, user);

        return user;
    }

private static void assignRole(UserDto userDto, User user) {
        if(userDto.getRole() == null){
            user.setRole(Role.TRAVELLER);
        }
        else {
            user.setRole(Role.ADMIN);
        }
    }

    private void checkIfUserExist(String email) throws UserAlreadyExistException {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            throw new UserAlreadyExistException("User with this email already exists");
        }
    }

    private void confirmPasswords(String password1, String password2) throws PasswordMismatchException {
        if(!(password1.equals(password2))){
            throw new PasswordMismatchException("Password mismatch");
        }
    }

    public String authenticateUser(String email, String password) throws InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (confirmUserPasswords(password, user.getPassword())) {
                return JwtTokenUtils.generateToken(user);
            } else {
                throw new InvalidCredentialsException("Incorrect password");
            }
        } else {
            throw new InvalidCredentialsException("User not found");
        }
    }

    private boolean confirmUserPasswords(String inputPassword, String storedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, storedPassword);
    }
}
