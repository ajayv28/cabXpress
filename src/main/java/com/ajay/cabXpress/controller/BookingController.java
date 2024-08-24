package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.exception.CabNotAvailableException;
import com.ajay.cabXpress.exception.CustomerNotFoundException;
import com.ajay.cabXpress.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity createBooking(BookingRequest bookingRequest) {

        try {
            BookingResponse savedBooking = bookingService.createBooking(bookingRequest);

            return new ResponseEntity(savedBooking, HttpStatus.CREATED);
        }catch(CustomerNotFoundException customerNotFoundException){
            return new ResponseEntity(customerNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(CabNotAvailableException cabNotAvailableException){
            return new ResponseEntity(cabNotAvailableException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
