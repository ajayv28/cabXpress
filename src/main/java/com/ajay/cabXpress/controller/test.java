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
@RequestMapping("/api")
public class test {

    @Autowired
    DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity registerDriver(@RequestBody DriverRequest driverRequest){
        DriverResponse savedDriver = driverService.registerDriver(driverRequest);
        return new ResponseEntity(savedDriver, HttpStatus.CREATED);
    }


}
