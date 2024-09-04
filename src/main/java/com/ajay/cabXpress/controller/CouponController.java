package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.dto.request.CouponRequest;
import com.ajay.cabXpress.dto.response.CouponResponse;
import com.ajay.cabXpress.service.CouponService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    //TESTED
    @PostMapping("/register")
    public ResponseEntity registerCoupon(@RequestBody CouponRequest couponRequest){
        CouponResponse response = couponService.registerCoupon(couponRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete")
    public ResponseEntity deleteCoupon(@RequestParam String couponCode){
        String response = couponService.deleteCoupon(couponCode);
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
