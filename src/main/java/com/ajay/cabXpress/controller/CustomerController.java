package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.exception.CabNotAvailableException;
import com.ajay.cabXpress.exception.CustomerNotFoundException;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.service.BookingService;
import com.ajay.cabXpress.service.CustomerService;
import com.ajay.cabXpress.service.DriverService;
import com.ajay.cabXpress.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.parameters.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    DriverService driverService;

    @Autowired
    BookingService bookingService;


    //TESTED
    @PostMapping("/create-booking")
    public ResponseEntity createBooking(@RequestBody BookingRequest bookingRequest, @AuthenticationPrincipal UserDetails userDetails) {

        try {
            String customerEmail = userDetails.getUsername();
            BookingResponse savedBooking = bookingService.createBooking(bookingRequest, customerEmail);

            return new ResponseEntity(savedBooking, HttpStatus.CREATED);
        }catch(CustomerNotFoundException customerNotFoundException){
            return new ResponseEntity(customerNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(CabNotAvailableException cabNotAvailableException){
            return new ResponseEntity(cabNotAvailableException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //TESTED
    @GetMapping("/all-booking")
    public ResponseEntity getAllBookingOfCurrentCustomer(@AuthenticationPrincipal UserDetails userDetails){
        String customerEmail = userDetails.getUsername();
        List<BookingResponse> response = customerService.getAllBookingOfCurrentCustomer(customerEmail);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrentCustomer(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int count){
        String customerEmail = userDetails.getUsername();
        List<BookingResponse> response = customerService.getLastNBookingOfCurrentCustomer(customerEmail, count);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PostMapping("/cancel-ride")
    public ResponseEntity cancelRide(@AuthenticationPrincipal UserDetails userDetails){
        String customerEmail = userDetails.getUsername();
        BookingResponse response = bookingService.cancelRide(customerEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/rate-driver")
    public ResponseEntity rateDriverOfLastCompletedTrip(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int rating){
        String customerEmail = userDetails.getUsername();
        String response = customerService.rateDriverOfLastCompletedTrip(customerEmail, rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal UserDetails userDetails){
        String customerEmail = userDetails.getUsername();
        String response = customerService.deleteCustomer(customerEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
