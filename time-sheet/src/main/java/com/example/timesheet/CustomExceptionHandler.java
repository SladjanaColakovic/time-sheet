package com.example.timesheet;

import com.example.timesheet.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<?> objectNotFound(){
        return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundException(){ return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND); }

    @ExceptionHandler(value = PasswordsDoNotMatchException.class)
    public ResponseEntity<?> passwordsDoNotMatchException(){ return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST); }

    @ExceptionHandler(value = InvalidPasswordFormatException.class)
    public ResponseEntity<?> invalidPasswordFormatException(){ return new ResponseEntity<>("Invalid password format", HttpStatus.BAD_REQUEST); }

    @ExceptionHandler(value = InvalidOldPasswordException.class)
    public ResponseEntity<?> invalidOldPasswordException(){ return new ResponseEntity<>("Invalid old password", HttpStatus.BAD_REQUEST); }
}
