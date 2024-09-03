package com.ajay.cabXpress.exception;

public class CustomerEngagedWithAnotherBookingException extends RuntimeException{

    public CustomerEngagedWithAnotherBookingException(String message){
        super(message);
    }

}
