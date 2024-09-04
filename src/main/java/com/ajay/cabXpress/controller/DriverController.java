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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    DriverService driverService;


    //TESTED
    @GetMapping("/all-booking")
    public ResponseEntity getAllBookingOfCurrentDriver(@AuthenticationPrincipal UserDetails userDetails){
        //if(userDetails != null)
        String driverEmail = userDetails.getUsername();
        List<BookingResponse> response = driverService.getAllBookingOfCurrentDriver(driverEmail);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrentDriver(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int count){
        String driverEmail = userDetails.getUsername();
        List<BookingResponse> response = driverService.getLastNBookingOfCurrentDriver(driverEmail, count);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteDriver(@AuthenticationPrincipal UserDetails userDetails){
        String driverEmail = userDetails.getUsername();
        String response = driverService.deleteDriver(driverEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    

}
