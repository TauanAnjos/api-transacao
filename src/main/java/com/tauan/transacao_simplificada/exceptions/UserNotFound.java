package com.tauan.transacao_simplificada.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound(String msg){
        super(msg);
    }
}
