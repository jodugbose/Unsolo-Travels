package com.interswitch.Unsolorockets.services.impl;

import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.dtos.responses.SignUpResponse;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.models.User;
import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.Role;
//import com.interswitch.Unsolorockets.respository.AdminRepository;
//import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.respository.UserRepository;
import com.interswitch.Unsolorockets.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private final TravellerRepository travellerRepository;
//    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException {
       checkIfUserExist(userDto.getEmail());
       confirmPasswords(userDto.getPassword(), userDto.getPassword2());
       User user = User.builder()
               .firstName(userDto.getFirstName())
               .lastName(userDto.getLastName())
               .dateOfBirth(userDto.getDateOfBirth())
               .email(userDto.getEmail())
               .gender(Gender.valueOf(userDto.getGender().toUpperCase()))
               .password(userDto.getPassword())
               .phoneNumber(userDto.getPhoneNumber())
               .build();

        assignRole(userDto, user);
        userRepository.save(user);
        return SignUpResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender().toString())
                .build();
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


}
