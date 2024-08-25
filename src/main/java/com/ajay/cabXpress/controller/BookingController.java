package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.exception.CabNotAvailableException;
import com.ajay.cabXpress.exception.CustomerNotFoundException;
import com.ajay.cabXpress.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

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

    
    @GetMapping("/n-booking")
    public ResponseEntity getLastNBooking(@RequestParam int n){
        List<BookingResponse> response = bookingService.getLastNBooking(n);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/total-fare")
    public ResponseEntity getBookingWithTotalFareGreaterThanX(@RequestParam float x){
        List<BookingResponse> response = bookingService.getBookingWithTotalFareGreaterThanX(x);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/booking-id")
    public ResponseEntity getBookingDetailWithBookingId(@RequestParam UUID bookingId){
        BookingResponse response = bookingService.getBookingDetailWithBookingId(bookingId);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/cancelled-booking")
    public ResponseEntity getAllCancelledBooking(){
        List<BookingResponse> response = bookingService.getAllCancelledBooking();
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/distance")
    public ResponseEntity getBookingWithTotalDistanceGreaterThanY(@RequestParam double y){
        List<BookingResponse> response = bookingService.getBookingWithTotalDistanceGreaterThanX(y);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/cab-type")
    public ResponseEntity getBookingWithGivenCabType(@RequestParam CabType cabType){
        List<BookingResponse> response = bookingService.getBookingWithGivenCabType(cabType);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    /*
    @GetMapping
    public ResponseEntity getBookingAfterGivenDate(@RequestParam Date date){
        List<BookingResponse> response = bookingService.getBookingAfterGivenDate(date);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBookingBetweenGivenDates(@RequestParam Date fromDate, @RequestParam Date toDate){
        List<BookingResponse> response = bookingService.getBookingBetweenGivenDates(fromDate, toDate);
            return new ResponseEntity(response, HttpStatus.OK);
    }


     */
    

}
