package com.interswitch.Unsolorockets.security;

import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.User;
import com.interswitch.Unsolorockets.respository.AdminRepository;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@AllArgsConstructor
@Service
@JsonComponent
public class CustomUserDetailService implements UserDetailsService {

    private final TravellerRepository travellerRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {

            Optional <User> userOptional = adminRepository.findByEmail(email);
            if(userOptional.isEmpty()){
                userOptional = travellerRepository.findByEmail(email);
            }
            if(userOptional.isEmpty()){
                throw new UserNotFoundException();
            }
            user = userOptional.get();

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
