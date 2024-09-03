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

    @PostMapping("/create-booking")
    public ResponseEntity createBooking(@RequestBody BookingRequest bookingRequest) {

        try {
            BookingResponse savedBooking = bookingService.createBooking(bookingRequest);

            return new ResponseEntity(savedBooking, HttpStatus.CREATED);
        }catch(CustomerNotFoundException customerNotFoundException){
            return new ResponseEntity(customerNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(CabNotAvailableException cabNotAvailableException){
            return new ResponseEntity(cabNotAvailableException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-booking")
    public ResponseEntity getAllBookingOfCurrentCustomer(@RequestParam String customerEmail){
        List<BookingResponse> response = customerService.getAllBookingOfCurrentCustomer(customerEmail);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrentCustomer(@RequestParam String customerEmail, @RequestParam int count){
        List<BookingResponse> response = customerService.getLastNBookingOfCurrentCustomer(customerEmail, count);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //testing not done
    @PostMapping
    public ResponseEntity cancelRide(@RequestParam String customerEmail){
        BookingResponse response = bookingService.cancelRide(customerEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTING NOT DONE
    @DeleteMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestParam String customerEmail){
        String response = customerService.deleteCustomer(customerEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //not tested
    @PutMapping("/rate-driver")
    public ResponseEntity rateDriverOfLastCompletedTrip(@RequestParam String customerEmail, @RequestParam int rating){
        String response = driverService.rateDriverOfLastCompletedTrip(customerEmail, rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
