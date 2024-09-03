package com.ajay.cabXpress.exception;

public class NoOngoingBookingFoundException extends RuntimeException{

    public NoOngoingBookingFoundException(String message){
        super(message);
    }
}
