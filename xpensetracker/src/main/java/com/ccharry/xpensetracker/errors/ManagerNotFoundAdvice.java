package com.ccharry.xpensetracker.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ManagerNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(ManagerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String managerNotFoundHandler(ManagerNotFoundException ex){
        return ex.getMessage();
    }
}
