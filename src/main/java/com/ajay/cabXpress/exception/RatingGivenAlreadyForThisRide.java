package com.ajay.cabXpress.exception;

public class RatingGivenAlreadyForThisRide extends RuntimeException{
    public RatingGivenAlreadyForThisRide(String message){
        super(message);
    }
}
