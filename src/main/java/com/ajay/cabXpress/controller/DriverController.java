package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping
    public ResponseEntity registerDriver(@RequestBody DriverRequest driverRequest){
        DriverResponse savedDriver = driverService.registerDriver(driverRequest);
        return new ResponseEntity(savedDriver, HttpStatus.CREATED);
    }
/*
    @GetMapping
    public ResponseEntity getAllBookingOfCurrentDriver(@RequestParam String email){
        List<DriverResponse> response = driverService.getAllBookingOfCurrentDriver(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity getLastNBookingOfCurrentDriver(@RequestParam String email){
        List<DriverResponse> response = driverService.getLastNBookingOfCurrentDriver(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    
    @GetMapping
    public ResponseEntity getDriverWithMoreThanNBooking(@RequestParam int n){
        List<DriverResponse> response = driverService.getDriverWithMoreThanNBooking(n);
            return new ResponseEntity(response, HttpStatus.OK);
    }
*/


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
    

}
