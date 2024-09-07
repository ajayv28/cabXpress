package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.CabRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.service.CabService;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;

    @Autowired
    DriverRepository driverRepository;

    //TESTED
    @PostMapping("/register-cab")
    public ResponseEntity registerCab(@RequestBody CabRequest cabRequest, @AuthenticationPrincipal UserDetails userDetails){

        try {
            String driverEmail = userDetails.getUsername();
            CabResponse savedCab = cabService.registerCab(cabRequest, driverEmail);
            return new ResponseEntity(savedCab, HttpStatus.CREATED);
        }catch(DriverNotFoundException de){
            return new ResponseEntity(de.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //TESTED
    @GetMapping("all-booking")
    public ResponseEntity getAllBookingOfCurrCab(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        List<BookingResponse> response = cabService.getAllBookingOfGivenCabNo(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfCurrCab(@AuthenticationPrincipal UserDetails userDetails, int count){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        List<BookingResponse> response = cabService.getLastNBookingOfGivenCabNo(cabNo,count);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @PutMapping("/make-unavailable")
    public ResponseEntity makeCabUnavailable(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        CabResponse response = cabService.makeCabUnavailable(cabNo);
            return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @PutMapping("/make-available")
    public ResponseEntity makeCabAvailable(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        CabResponse response = cabService.makeCabAvailable(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/change-fare")
    public ResponseEntity changeFarePerKmOfCurrCab(@AuthenticationPrincipal UserDetails userDetails, double newFarePerKm){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        CabResponse response = cabService.changeFarePerKm(cabNo, newFarePerKm);
            return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/start-trip")
    public ResponseEntity startTripOfCurrCab(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        BookingResponse response = cabService.startTrip(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/end-trip")
    public ResponseEntity endTripOfCurrCab(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String cabNo = currDriver.getCab().getCabNo();
        BookingResponse response = cabService.endTrip(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @DeleteMapping("/delete")
    public ResponseEntity deleteCab(@AuthenticationPrincipal UserDetails userDetails){
        Driver currDriver = driverRepository.findByEmail(userDetails.getUsername());
        String response = cabService.deleteCab(currDriver);
        return new ResponseEntity(response, HttpStatus.OK);
    }


}
