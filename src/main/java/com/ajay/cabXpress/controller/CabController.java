package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.service.CabService;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;



    @GetMapping("all-booking")
    public ResponseEntity getAllBookingOfGivenCabNo(@RequestParam String cabNo){
        List<BookingResponse> response = cabService.getAllBookingOfGivenCabNo(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfGivenCabNo(@RequestParam String cabNo, int count){
        List<BookingResponse> response = cabService.getLastNBookingOfGivenCabNo(cabNo,count);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    @PutMapping("/make-unavailable")
    public ResponseEntity makeCabUnavailable(@RequestParam String cabNo){
        CabResponse response = cabService.makeCabUnavailable(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/make-available")
    public ResponseEntity makeCabAvailable(@RequestParam String cabNo){
        CabResponse response = cabService.makeCabAvailable(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/change-fare")
    public ResponseEntity changeFarePerKm(@RequestParam String cabNo, double newFarePerKm){
        CabResponse response = cabService.changeFarePerKm(cabNo, newFarePerKm);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //NOT TESTED
    @PutMapping("/end-trip")
    public ResponseEntity endTrip(@RequestParam String cabNo){
        BookingResponse response = cabService.endTrip(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //NOT TESTED
    @PutMapping("/start-trip")
    public ResponseEntity startTrip(@RequestParam String cabNo){
        BookingResponse response = cabService.startTrip(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
