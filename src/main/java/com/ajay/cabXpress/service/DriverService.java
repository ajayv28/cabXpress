package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.BookingTransformer;
import com.ajay.cabXpress.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;


    public DriverResponse registerDriver(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }

    public List<DriverResponse> getAllDriverByAgeAboveN(int n) {
        List<Driver> driver = driverRepository.getAllDriverByAgeAboveN(n);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverByGender(Gender gender) {
        List<Driver> driver = driverRepository.getAllDriverByGender(gender.toString());
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverByGenderAndAgeBelowN(Gender gender, int n) {
        List<Driver> driver = driverRepository.getAllDriverByGenderAndAgeBelowN(gender.toString(), n);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverWithCabRegistered() {
        List<Driver> driver = driverRepository.getAllDriverWithCabRegistered();
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverWithNoCabRegistered() {
        List<Driver> driver = driverRepository.getAllDriverWithNoCabRegistered();
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<BookingResponse> getAllBookingOfCurrentDriver(String email) {
        int driverId = driverRepository.findByEmail(email).getId();
        List<Booking> booking = bookingRepository.getAllBookingOfCurrentDriver(driverId);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings : booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }

        return response;
    }

    public List<BookingResponse> getLastNBookingOfCurrentDriver(String email, int n) {
        int driverId = driverRepository.findByEmail(email).getId();
        List<Booking> booking = bookingRepository.getLastNBookingOfCurrentDriver(driverId, n);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings : booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }

        return response;
    }

    public List<DriverResponse> getDriverWithMoreThanNBooking(int n) {
        List<Driver> drivers = driverRepository.getDriverWithMoreThanNBooking(n);
        List<DriverResponse> response = new ArrayList<>();

        for(Driver driver : drivers){
            response.add(DriverTransformer.driverToDriverResponse(driver));
        }

        return response;
    }


    public List<DriverResponse> getAllDriverByRating(int rating) {
        List<Driver> driver = driverRepository.getAllDriverByRating(rating);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public String deleteDriver(String driverEmail) {
        Driver driver = driverRepository.findByEmail(driverEmail);
        driverRepository.delete(driver);
        return "Successfully deleted from our database";
    }
}
