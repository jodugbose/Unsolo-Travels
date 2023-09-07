package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.OTPRequest;
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
import com.interswitch.Unsolorockets.service.EmailService;
import com.interswitch.Unsolorockets.service.UserService;
import com.interswitch.Unsolorockets.utils.AppUtils;
import com.interswitch.Unsolorockets.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private JwtTokenUtils jwtTokenUtils;

    private final IPasswordEncoder passwordEncoder;

    private final EmailService emailService;
    private final HttpServletRequest request;
    private final AppUtils appUtils;

    @Override
    public SignUpResponse createUser(UserDto userDto) throws UserAlreadyExistException, PasswordMismatchException, IOException {
        checkIfUserExist(userDto.getEmail());

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        String date = userDto.getDay()+ "-"+ userDto.getMonth()+ "-"+ userDto.getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateOfBirth = LocalDate.parse(date, formatter);
        User createdUser = createUserFromDto(userDto, encodedPassword);
        createdUser.setDateOfBirth(dateOfBirth);

        String token = JwtTokenUtils.generateEmailVerificationToken(createdUser.getEmail());
        createdUser.setTokenForEmail(token);

        String otp = String.valueOf(appUtils.generateOTP());
        createdUser.setValidOTP(passwordEncoder.encode(otp));

        String url = "http://" + request.getServerName() + ":8080" + "/api/v1/verify-email?token="
                + token + "&email="+ userDto.getEmail();


        String email = createdUser.getEmail();
        String subject = "Unsolo: Verify Profile";
        String body =
                "<html> " +
                        "<body>" +
                        "<h4>Hi " + createdUser.getFirstName() + " " + createdUser.getLastName() +",</h4> \n" +
                        "<p>Welcome to Unsolo.\n" +
                        "To activate your Unsolo Account, verify your email address by clicking " +
                        "<a href="+url+">verify</a></p>" +
                        "</body> " +
                        "</html>";
        emailService.sendMail(email, subject, body, "text/html");

        User user = userRepository.save(createdUser);

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
                .email(userDto.getEmail())
                .gender(Gender.valueOf(userDto.getGender().toUpperCase()))
                .password(encodedPassword)
                .build();

        assignRole(userDto, user);

        return user;
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

    @Override
    public String verifyOTP(OTPRequest otpRequest) {
        User user = userRepository.findByEmail(otpRequest.getEmailForOTP()).get();
        if (user.isVerified()){
            return "This account is already verified";
        }

        if (passwordEncoder.matches(otpRequest.getOtp(), user.getValidOTP())){
            user.setVerified(true);
            userRepository.save(user);
            return "Verification successful";
        }else {
            return "Check the OTP and try again";
        }
    }
    private boolean confirmUserPasswords(String inputPassword, String storedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(inputPassword, storedPassword);
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
    private static void assignRole(UserDto userDto, User user) {
        if(userDto.getRole() == null){
            user.setRole(Role.TRAVELLER);
        }
        else {
            user.setRole(Role.ADMIN);
        }
    }
}
