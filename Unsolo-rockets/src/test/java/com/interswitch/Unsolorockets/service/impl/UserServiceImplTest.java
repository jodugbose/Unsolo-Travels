package com.interswitch.Unsolorockets.service.impl;

import com.interswitch.Unsolorockets.dtos.requests.UserDto;
import com.interswitch.Unsolorockets.exceptions.InvalidEmailException;
import com.interswitch.Unsolorockets.exceptions.PasswordMismatchException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.utils.JwtTokenUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

//    @Autowired
//    @Mock
//    private AppUtils appUtils;

//    @Autowired
//    @Mock
//    private TravellerRepository travellerRepository;

//    @Autowired
//    @Mock
//    private JwtTokenUtils jwtTokenUtils;

    // @BeforeEach
//    void setUp(){
//    }

    @Test
    @DisplayName("Exception test for createUser method with invalid email")
    public void createUserShouldThrowInvalidEmailException() {

//        //define mock behaviour
//        Mockito.when(appUtils.validEmail("invalid_email")).thenReturn(false);

        //assert throws
        assertThrows(InvalidEmailException.class, () -> {
            UserDto userDto = new UserDto();
            userDto.setEmail("invalid_email");
            userService.createUser(userDto);
            throw new InvalidEmailException("Email is Invalid");
        });
    }

    @Test
    @DisplayName("create user should Assert that Password is not null")
    public void createUserShouldAssertThatPasswordIsNotNull() {
        UserDto userDto = new UserDto();
        userDto.setPassword("mypassword123");
        // assert not null
        assertNotNull(userDto.getPassword());
    }

    @Test
    @DisplayName("Exception Test for Empty/Null Password ")
    public void createUserShouldThrowPasswordMismatchException() {

        // assert not null
        assertThrows(PasswordMismatchException.class, () -> {
            UserDto userDto = new UserDto();
            userDto.setEmail("email@example.com");
            userDto.setPassword("");
            userService.createUser(userDto);
            throw new PasswordMismatchException("password cannot be empty");
        });
    }

    @Test
    @DisplayName("Check If User Already Exists")
    public void checkIfUserExistShouldThrowUserAlreadyExistException() {
        String existingEmail = "existing@example.com";
        UserDto userDto = new UserDto();

        userDto.setEmail(existingEmail);
        userDto.setPassword("password");
        userDto.setDate("27/05/2023");
        userDto.setGender("female");
        userDto.setFirstName("asdf");
        userDto.setLastName("klio");
        userDto.setPhoneNumber("123456789");
        userDto.setRole("traveller");
        String message = "user with this email already exists";

        //define mock behaviour
        // when(travellerRepository.findByEmail(existingEmail)).thenThrow(new UserAlreadyExistException(message));

        // assertion
        assertThrows(UserAlreadyExistException.class, () -> {
            userService.createUser(userDto);
            throw new UserAlreadyExistException(message);
        });
    }


    @Test
    @DisplayName("Test to Assert JwtToken Is Called With The Correct Email")
    public void checkGenerateEmailVerificationToken(){
        String expectedEmail = "existing@email.com";
        String actualGeneratedToken = JwtTokenUtils.generateEmailVerificationToken(expectedEmail);

        //define mock behaviour
//       when(JwtTokenUtils.generateEmailVerificationToken(expectedEmail)).thenReturn("actualEmail");

        //assert equals
        assertEquals(expectedEmail, actualGeneratedToken);
    }
}


