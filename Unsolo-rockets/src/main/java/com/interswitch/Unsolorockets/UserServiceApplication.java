package com.interswitch.Unsolorockets;

import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.enums.Gender;
import com.interswitch.Unsolorockets.models.enums.Role;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext  =	SpringApplication.run(UserServiceApplication.class, args);

//		TravellerRepository travellerRepository = applicationContext.getBean(TravellerRepository.class);
//
//		Traveller user = new Traveller();
//		user.setId(1L); // Set the ID as per your requirement
//		user.setFirstName("John");
//		user.setLastName("Doe");
//		user.setPassword("mySecretPassword");
//		user.setPhoneNumber("1234567890");
//		user.setEmail("john.doe@example.com");
//		user.setGender(Gender.MALE);
//		user.setVerified(true);
//		user.setDateOfBirth(LocalDate.of(1990, 5, 15));
//		user.setRole(Role.TRAVELLER);
//		user.setValidOTP("123456"); // Set a valid OTP if needed
//		user.setTokenForEmail("token123"); // Set an email token if needed
//		user.setCreatedAt(new Date()); // Set the creation date as needed
//
//travellerRepository.save(user);



	}

}
