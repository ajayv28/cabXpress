package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.service.CabService;
import com.ajay.cabXpress.service.CustomerService;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;

    @Autowired
    CustomerService customerService;


    // DRIVER

    @GetMapping("/morethan-n-booking")
    public ResponseEntity getDriverWithMoreThanNBooking(@RequestParam int n){
        List<DriverResponse> response = driverService.getDriverWithMoreThanNBooking(n);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/age")
    public ResponseEntity getAllDriverByAgeAboveN(@RequestParam int n){
        List<DriverResponse> response = driverService.getAllDriverByAgeAboveN(n);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/gender")
    public ResponseEntity getAllDriverByGender(@RequestParam Gender gender){
        List<DriverResponse> response = driverService.getAllDriverByGender(gender);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/gender-age")
    public ResponseEntity getAllDriverByGenderAndAgeBelowN(@RequestParam Gender gender, @RequestParam int n){
        List<DriverResponse> response = driverService.getAllDriverByGenderAndAgeBelowN(gender, n);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/cab-registered")
    public ResponseEntity getAllDriverWithCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/cab-not-registered")
    public ResponseEntity getAllDriverWithNoCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithNoCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // CUSTOMER


    @GetMapping("/registered-date")
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/more-than-n-booking")
    public ResponseEntity getAllCustomerWithMoreThanNBooking(@RequestParam int n){
        List<CustomerResponse> response = customerService.getAllCustomerWithMoreThanNBooking(n);
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
