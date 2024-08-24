package com.ajay.cabXpress.exception;

import com.ajay.cabXpress.model.Cab;

public class CabNotAvailableException extends RuntimeException{

    public CabNotAvailableException(String message){
        super(message);
    }
}
