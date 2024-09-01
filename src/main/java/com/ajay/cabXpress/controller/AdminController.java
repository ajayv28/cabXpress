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

    @GetMapping("/driver-more-than-n-booking")
    public ResponseEntity getDriverWithMoreThanNBooking(@RequestParam int driverBookingCount){
        List<DriverResponse> response = driverService.getDriverWithMoreThanNBooking(driverBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-age")
    public ResponseEntity getAllDriverByAgeAboveN(@RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByAgeAboveN(driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-gender")
    public ResponseEntity getAllDriverByGender(@RequestParam Gender driverGender){
        List<DriverResponse> response = driverService.getAllDriverByGender(driverGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-gender-age")
    public ResponseEntity getAllDriverByGenderAndAgeBelowN(@RequestParam Gender driverGender, @RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByGenderAndAgeBelowN(driverGender, driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/driver-cab-registered")
    public ResponseEntity getAllDriverWithCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-cab-not-registered")
    public ResponseEntity getAllDriverWithNoCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithNoCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // CUSTOMER


    @GetMapping("/customer-registered-date")
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //not tested
    @GetMapping("/customer-more-than-n-booking")
    public ResponseEntity getAllCustomerWithMoreThanNBooking(@RequestParam int customerBookingCount){
        List<CustomerResponse> response = customerService.getAllCustomerWithMoreThanNBooking(customerBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/customer-age-above")
    public ResponseEntity getAllCustomerByAgeAboveN(@RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByAgeAboveN(customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/customer-gender")
    public ResponseEntity getAllCustomerByGender(@RequestParam Gender customerGender){
        List<CustomerResponse> response = customerService.getAllCustomerByGender(customerGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/customer-gender-age")
    public ResponseEntity getAllCustomerByGenderAndAgeBelowN(@RequestParam Gender customerGender, @RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByGenderAndAgeBelowN(customerGender, customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
