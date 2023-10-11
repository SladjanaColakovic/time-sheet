package com.example.timesheet;

import com.example.timesheet.core.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoreExceptionHandler {
    
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<?> objectNotFound(){
        return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
    }
}
