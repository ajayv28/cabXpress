package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    @Autowired
    CabService cabService;

    @Autowired
    DriverService driverService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AdminService adminService;

    @Autowired
    BookingService bookingService;

    //not tested
    @GetMapping("daily-task")
    public ResponseEntity adminDailyTask(){
        String response = adminService.adminDailyTask();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // DRIVER
    @GetMapping("/driver-more-than-n-booking")
    public ResponseEntity getDriverWithMoreThanNBooking(@RequestParam int driverBookingCount){
        List<DriverResponse> response = driverService.getDriverWithMoreThanNBooking(driverBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-age")
    public ResponseEntity getAllDriverByAgeAboveN(@RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByAgeAboveN(driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-gender")
    public ResponseEntity getAllDriverByGender(@RequestParam Gender driverGender){
        List<DriverResponse> response = driverService.getAllDriverByGender(driverGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-gender-age")
    public ResponseEntity getAllDriverByGenderAndAgeBelowN(@RequestParam Gender driverGender, @RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByGenderAndAgeBelowN(driverGender, driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/driver-cab-registered")
    public ResponseEntity getAllDriverWithCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/driver-cab-not-registered")
    public ResponseEntity getAllDriverWithNoCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithNoCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //not tested
    @GetMapping("/driver-by-rating")
    public ResponseEntity getAllDriverByRating(@RequestParam int rating){
        List<DriverResponse> response = driverService.getAllDriverByRating(rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // CUSTOMER


    @GetMapping("/customer-registered-date")
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //not tested
    @GetMapping("/customer-more-than-n-booking")
    public ResponseEntity getAllCustomerWithMoreThanNBooking(@RequestParam int customerBookingCount){
        List<CustomerResponse> response = customerService.getAllCustomerWithMoreThanNBooking(customerBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/customer-age-above")
    public ResponseEntity getAllCustomerByAgeAboveN(@RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByAgeAboveN(customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/customer-gender")
    public ResponseEntity getAllCustomerByGender(@RequestParam Gender customerGender){
        List<CustomerResponse> response = customerService.getAllCustomerByGender(customerGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/customer-gender-age")
    public ResponseEntity getAllCustomerByGenderAndAgeBelowN(@RequestParam Gender customerGender, @RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByGenderAndAgeBelowN(customerGender, customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

   //BOOKINGS

    //NOT TESTED
    @GetMapping("/active-rides")
    public ResponseEntity getAllActiveRides(){
        List<BookingResponse> response = bookingService.getAllActiveRides();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //not tested
    @GetMapping("/booking-by-rating")
    public ResponseEntity getAllBookingByRating(@RequestParam int rating){
        List<BookingResponse> response = bookingService.getAllBookingByRating(rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/n-booking")
    public ResponseEntity getLastNBooking(@RequestParam int n){
        List<BookingResponse> response = bookingService.getLastNBooking(n);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/booking-total-fare")
    public ResponseEntity getBookingWithTotalFareGreaterThanX(@RequestParam float x){
        List<BookingResponse> response = bookingService.getBookingWithTotalFareGreaterThanX(x);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/booking-id")
    public ResponseEntity getBookingDetailWithBookingId(@RequestParam String bookingId){
        BookingResponse response = bookingService.getBookingDetailWithBookingId(bookingId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/cancelled-booking")
    public ResponseEntity getAllCancelledBooking(){
        List<BookingResponse> response = bookingService.getAllCancelledBooking();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/distance")
    public ResponseEntity getBookingWithTotalDistanceGreaterThanY(@RequestParam double y){
        List<BookingResponse> response = bookingService.getBookingWithTotalDistanceGreaterThanX(y);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/booking-cab-type")
    public ResponseEntity getBookingWithGivenCabType(@RequestParam CabType cabType){
        List<BookingResponse> response = bookingService.getBookingWithGivenCabType(cabType);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/booking-after-date")
    public ResponseEntity getBookingAfterGivenDate(@RequestParam Date date){
        List<BookingResponse> response = bookingService.getBookingAfterGivenDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/booking-between-dates")
    public ResponseEntity getBookingBetweenGivenDates(@RequestParam Date fromDate, @RequestParam Date toDate){
        List<BookingResponse> response = bookingService.getBookingBetweenGivenDates(fromDate, toDate);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
