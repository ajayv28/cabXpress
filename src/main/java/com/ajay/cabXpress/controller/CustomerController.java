package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.service.CustomerService;
import com.ajay.cabXpress.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity registerCustomer(@RequestBody CustomerRequest customerRequest){

        CustomerResponse savedCustomer = customerService.registerCustomer(customerRequest);

        return new ResponseEntity(savedCustomer, HttpStatus.CREATED);

    }

    /*
    @GetMapping
    public ResponseEntity getAllBookingOfCurrentCustomer(@RequestParam String email){
        List<CustomerResponse> response = customerService.getAllBookingOfCurrentCustomer(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getLastNBookingOfCurrentCustomer(@RequestParam String email){
        List<CustomerResponse> response = customerService.getLastNBookingOfCurrentCustomer(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    */
    @GetMapping("/registered-date")
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
            return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping("/n-booking")
    public ResponseEntity getCustomerWithMoreThanNBooking(@RequestParam int n){
        List<CustomerResponse> response = customerService.getCustomerWithMoreThanNBooking(n);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/age-above")
    public ResponseEntity getAllCustomerByAgeAboveN(@RequestParam int age){
         List<CustomerResponse> response = customerService.getAllCustomerByAgeAboveN(age);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/gender")
    public ResponseEntity getAllCustomerByGender(@RequestParam Gender gender){
         List<CustomerResponse> response = customerService.getAllCustomerByGender(gender);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/gender-age")
    public ResponseEntity getAllCustomerByGenderAndAgeBelowN(@RequestParam Gender gender, @RequestParam int age){
         List<CustomerResponse> response = customerService.getAllCustomerByGenderAndAgeBelowN(gender, age);
            return new ResponseEntity(response, HttpStatus.OK);
    }
    

}
