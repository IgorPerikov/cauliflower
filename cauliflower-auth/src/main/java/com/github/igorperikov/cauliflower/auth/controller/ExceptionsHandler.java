package com.github.igorperikov.cauliflower.auth.controller;

import com.github.igorperikov.cauliflower.auth.exception.IncorrectCredentialsException;
import com.github.igorperikov.cauliflower.auth.exception.LoginAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(LoginAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handle(LoginAlreadyTakenException e) {}

    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handle(IncorrectCredentialsException e) {}
}
