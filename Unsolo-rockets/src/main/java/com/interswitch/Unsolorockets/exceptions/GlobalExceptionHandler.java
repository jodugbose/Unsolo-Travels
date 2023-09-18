package com.interswitch.Unsolorockets.exceptions;

//import com.interswitch.Unsolorockets.dtos.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler  {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlesAccessDeniedException(AccessDeniedException accessDeniedException){
        return new ResponseEntity<>(accessDeniedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CommonsException.class)
    public ResponseEntity<?> handlesCommonsException(CommonsException commonsException){
        return new ResponseEntity<>(commonsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handlesJwtException(JwtException jwtException){
        return new ResponseEntity<>(jwtException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> handlesPasswordMismatchException(PasswordMismatchException passwordMismatchException){
        return new ResponseEntity<>(passwordMismatchException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handlesUserAlreadyExistExceptions(UserAlreadyExistException userAlreadyExistException){


        System.out.println(userAlreadyExistException.getMessage() + " ==================================");
        return new ResponseEntity<>(userAlreadyExistException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handlesUserException(UserException userException){
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlesUserNotFoundExceptions(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> handleInvalidEmailException(InvalidEmailException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<?> handleTripNotFoundException(TripNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PackageException.class)
    public ResponseEntity<?> handlePackageException(PackageException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
