package com.interswitch.Unsolorockets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlesAccessDeniedException(AccessDeniedException accessDeniedException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(accessDeniedException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CommonsException.class)
    public ResponseEntity<?> handlesCommonsException(CommonsException commonsException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(commonsException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handlesJwtException(JwtException jwtException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(jwtException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> handlesPasswordMismatchException(PasswordMismatchException passwordMismatchException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(passwordMismatchException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handlesUserAlreadyExistExceptions(UserAlreadyExistException userAlreadyExistException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(userAlreadyExistException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handlesUserException(UserException userException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(userException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlesUserNotFoundExceptions(UserNotFoundException userNotFoundException){
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(userNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ApiResponse apiResponse = ApiResponse.builder()
                .success(false)
                .data(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
}
