package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.service.CustomerService;
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


    @GetMapping("/all-booking")
    public ResponseEntity getAllBookingOfCurrentCustomer(@RequestParam String email){
        List<BookingResponse> response = customerService.getAllBookingOfCurrentCustomer(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrentCustomer(@RequestParam String email, @RequestParam int n){
        List<BookingResponse> response = customerService.getLastNBookingOfCurrentCustomer(email, n);
            return new ResponseEntity(response, HttpStatus.OK);
    }

}
