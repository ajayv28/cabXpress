package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.service.CabService;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;

    @PostMapping
    public ResponseEntity registerCab(@RequestBody CabRequest cabRequest){

        try {
            CabResponse savedCab = cabService.registerCab(cabRequest);
            return new ResponseEntity(savedCab, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
