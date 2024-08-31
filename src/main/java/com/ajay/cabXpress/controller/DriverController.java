package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
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


    @GetMapping("/all-booking")
    public ResponseEntity getAllBookingOfCurrentDriver(@RequestParam String email){
        List<BookingResponse> response = driverService.getAllBookingOfCurrentDriver(email);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrentDriver(@RequestParam String email, @RequestParam int n){
        List<BookingResponse> response = driverService.getLastNBookingOfCurrentDriver(email, n);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    

}
