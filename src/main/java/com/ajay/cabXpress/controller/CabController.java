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
@RequestMapping("/api/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity registerCab(@RequestBody CabRequest cabRequest){

        try {
            CabResponse savedCab = cabService.registerCab(cabRequest);
             return new ResponseEntity(savedCab, HttpStatus.CREATED);
       }catch(DriverNotFoundException de){
            return new ResponseEntity(de.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all-booking")
    public ResponseEntity getAllBookingOfGivenCabNo(@RequestParam String cabNo){
        List<BookingResponse> response = cabService.getAllBookingOfGivenCabNo(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfGivenCabNo(@RequestParam String cabNo, int n){
        List<BookingResponse> response = cabService.getLastNBookingOfGivenCabNo(cabNo,n);
            return new ResponseEntity(response, HttpStatus.OK);
    }



    @PutMapping("/make-unavailable")
    public ResponseEntity makeCabUnavailable(@RequestParam String cabNo){
        CabResponse response = cabService.makeCabUnavailable(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/change-fare")
    public ResponseEntity changeFarePerKm(@RequestParam String cabNo, double newFarePerKm){
        CabResponse response = cabService.changeFarePerKm(cabNo, newFarePerKm);
            return new ResponseEntity(response, HttpStatus.OK);
    }



}
