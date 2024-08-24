package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
