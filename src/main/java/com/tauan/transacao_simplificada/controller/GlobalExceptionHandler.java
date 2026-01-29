package com.tauan.transacao_simplificada.controller;

import com.tauan.transacao_simplificada.exceptions.BadRequestException;
import com.tauan.transacao_simplificada.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleIllegalAccessException(IllegalAccessException e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFound e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
