package com.ajay.cabXpress.controller;

import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    DriverRepository driverRepository;


    @GetMapping("daily-task")
    public ResponseEntity adminDailyTask(){
        String response = adminService.adminDailyTask();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // ******************** DRIVER ******************


    //TESTED
    @GetMapping("/driver-more-than-n-booking")
    public ResponseEntity getDriverWithMoreThanNBooking(@RequestParam int driverBookingCount){
        List<DriverResponse> response = driverService.getDriverWithMoreThanNBooking(driverBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-age")
    public ResponseEntity getAllDriverByAgeAboveN(@RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByAgeAboveN(driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-gender")
    public ResponseEntity getAllDriverByGender(@RequestParam Gender driverGender){
        List<DriverResponse> response = driverService.getAllDriverByGender(driverGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-gender-age")
    public ResponseEntity getAllDriverByGenderAndAgeBelowN(@RequestParam Gender driverGender, @RequestParam int driverAge){
        List<DriverResponse> response = driverService.getAllDriverByGenderAndAgeBelowN(driverGender, driverAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-cab-registered")
    public ResponseEntity getAllDriverWithCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-cab-not-registered")
    public ResponseEntity getAllDriverWithNoCabRegistered(){
        List<DriverResponse> response = driverService.getAllDriverWithNoCabRegistered();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/driver-by-rating")
    public ResponseEntity getAllDriverByRating(@RequestParam int rating){
        List<DriverResponse> response = driverService.getAllDriverByRating(rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/driver-all-booking")
    public ResponseEntity getAllBookingOfDriver(@RequestParam String driverEmail){
        List<BookingResponse> response = driverService.getAllBookingOfCurrentDriver(driverEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/driver-last-n-booking")
    public ResponseEntity getLastNBookingOfDriver(@RequestParam String driverEmail, @RequestParam int count){
        List<BookingResponse> response = driverService.getLastNBookingOfCurrentDriver(driverEmail, count);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    // ******************** CUSTOMER ****************************


    //TESTED
    @GetMapping("/customer-registered-date")
    public ResponseEntity getAllCustomerRegisteredAfterSpecificDate(@RequestParam Date date){
        List<CustomerResponse> response = customerService.getAllCustomerRegisteredAfterSpecificDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-more-than-n-booking")
    public ResponseEntity getAllCustomerWithMoreThanNBooking(@RequestParam int customerBookingCount){
        List<CustomerResponse> response = customerService.getAllCustomerWithMoreThanNBooking(customerBookingCount);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-age-above")
    public ResponseEntity getAllCustomerByAgeAboveN(@RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByAgeAboveN(customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-gender")
    public ResponseEntity getAllCustomerByGender(@RequestParam Gender customerGender){
        List<CustomerResponse> response = customerService.getAllCustomerByGender(customerGender);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-gender-age")
    public ResponseEntity getAllCustomerByGenderAndAgeBelowN(@RequestParam Gender customerGender, @RequestParam int customerAge){
        List<CustomerResponse> response = customerService.getAllCustomerByGenderAndAgeBelowN(customerGender, customerAge);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-all-booking")
    public ResponseEntity getAllBookingOfCustomer(@RequestParam String customerEmail){
        List<BookingResponse> response = customerService.getAllBookingOfCurrentCustomer(customerEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/customer-last-n-booking")
    public ResponseEntity getLastNBookingOfCustomer(@RequestParam String customerEmail, @RequestParam int count){
        List<BookingResponse> response = customerService.getLastNBookingOfCurrentCustomer(customerEmail, count);
        return new ResponseEntity(response, HttpStatus.OK);
    }


        // ********************** BOOKINGS ***************************


    //TESTED
    @GetMapping("/active-rides")
    public ResponseEntity getAllActiveRides(){
        List<BookingResponse> response = bookingService.getAllActiveRides();
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/booking-by-rating")
    public ResponseEntity getAllBookingByRating(@RequestParam int rating){
        List<BookingResponse> response = bookingService.getAllBookingByRating(rating);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/n-booking")
    public ResponseEntity getLastNBooking(@RequestParam int n){
        List<BookingResponse> response = bookingService.getLastNBooking(n);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/booking-total-fare")
    public ResponseEntity getBookingWithTotalFareGreaterThanX(@RequestParam float x){
        List<BookingResponse> response = bookingService.getBookingWithTotalFareGreaterThanX(x);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/booking-id")
    public ResponseEntity getBookingDetailWithBookingId(@RequestParam String bookingId){
        BookingResponse response = bookingService.getBookingDetailWithBookingId(bookingId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/cancelled-booking")
    public ResponseEntity getAllCancelledBooking(){
        List<BookingResponse> response = bookingService.getAllCancelledBooking();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/distance")
    public ResponseEntity getBookingWithTotalDistanceGreaterThanY(@RequestParam double y){
        List<BookingResponse> response = bookingService.getBookingWithTotalDistanceGreaterThanX(y);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/booking-cab-type")
    public ResponseEntity getBookingWithGivenCabType(@RequestParam CabType cabType){
        List<BookingResponse> response = bookingService.getBookingWithGivenCabType(cabType);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/booking-after-date")
    public ResponseEntity getBookingAfterGivenDate(@RequestParam Date date){
        List<BookingResponse> response = bookingService.getBookingAfterGivenDate(date);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @GetMapping("/booking-between-dates")
    public ResponseEntity getBookingBetweenGivenDates(@RequestParam Date fromDate, @RequestParam Date toDate){
        List<BookingResponse> response = bookingService.getBookingBetweenGivenDates(fromDate, toDate);
        return new ResponseEntity(response, HttpStatus.OK);
    }

         // ********************** CAB *************************


    //TESTED
    @GetMapping("all-booking")
    public ResponseEntity getAllBookingOfGivenCabNo(@RequestParam String cabNo){
        List<BookingResponse> response = cabService.getAllBookingOfGivenCabNo(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @GetMapping("/last-n-booking")
    public ResponseEntity getLastNBookingOfGivenCabNo(@RequestParam String cabNo, int count){
        List<BookingResponse> response = cabService.getLastNBookingOfGivenCabNo(cabNo,count);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @PutMapping("/make-unavailable")
    public ResponseEntity makeCabUnavailable(@RequestParam String cabNo){
        CabResponse response = cabService.makeCabUnavailable(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/make-available")
    public ResponseEntity makeCabAvailable(@RequestParam String cabNo){
        CabResponse response = cabService.makeCabAvailable(cabNo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    //TESTED
    @PutMapping("/change-fare")
    public ResponseEntity changeFarePerKm(@RequestParam String cabNo, double newFarePerKm){
        CabResponse response = cabService.changeFarePerKm(cabNo, newFarePerKm);
        return new ResponseEntity(response, HttpStatus.OK);
    }




         // *************  DELETE  **************************


    //TESTED
    @DeleteMapping("/delete-driver")
    public ResponseEntity deleteDriver(@AuthenticationPrincipal UserDetails userDetails){
        String driverEmail = userDetails.getUsername();
        String response = driverService.deleteDriver(driverEmail);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    //TESTED
    @DeleteMapping("/delete-customer")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal UserDetails userDetails){
        String customerEmail = userDetails.getUsername();
        String response = customerService.deleteCustomer(customerEmail);
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
