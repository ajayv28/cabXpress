package com.ajay.cabXpress.exception;

public class CabAlreadyRegisteredException extends RuntimeException{
    public CabAlreadyRegisteredException(String message){
        super(message);
    }
}
