package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.service.CabService;
import com.ajay.cabXpress.service.CustomerService;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    DriverService driverService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CabService cabService;

    //TESTED
    @PostMapping("/customer")
    public ResponseEntity registerCustomer(@RequestBody CustomerRequest customerRequest){

        CustomerResponse savedCustomer = customerService.registerCustomer(customerRequest);

        return new ResponseEntity(savedCustomer, HttpStatus.CREATED);

    }

    //TESTED
    @PostMapping("/driver")
    public ResponseEntity registerDriver(@RequestBody DriverRequest driverRequest){
        DriverResponse savedDriver = driverService.registerDriver(driverRequest);
        return new ResponseEntity(savedDriver, HttpStatus.CREATED);
    }

}
