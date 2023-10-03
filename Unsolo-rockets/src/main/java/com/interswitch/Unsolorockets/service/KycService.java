package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.exceptions.InvalidNinValidationException;
import com.interswitch.Unsolorockets.exceptions.UserAlreadyExistException;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.models.User;
import reactor.core.publisher.Mono;

public interface KycService {
    String ninValidationRequest(String ninId) throws InvalidNinValidationException,
            UserAlreadyExistException;

}
