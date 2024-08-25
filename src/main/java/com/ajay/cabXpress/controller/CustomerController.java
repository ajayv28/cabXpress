package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.service.CustomerService;
import com.ajay.cabXpress.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity getAllBookingOfCurrentCustomer(@RequestParam long mobileNumber){
        List<CustomerResponse> response = customerService.getAllBookingOfCurrentCustomer(mobileNumber);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
            return new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity getCustomerWithMoreThanNBooking(@RequestParam int n){
        List<CustomerResponse> response = customerService.getCustomerWithMoreThanNBooking(n);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCustomerByAgeAboveN(@RequestParam int age){
         List<CustomerResponse> response = customerService.getAllCustomerByAgeAboveN(age);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCustomerByGender(@RequestParam Gender gender){
         List<CustomerResponse> response = customerService.getAllCustomerByGender(gender);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCustomerByGenderAndAge(@RequestParam Gender gender, @RequestParam int age){
         List<CustomerResponse> response = customerService.getAllCustomerByGenderAndAge(gender, age);
            return new ResponseEntity(response, HttpStatus.OK);
    }
    
    */
}