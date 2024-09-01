package com.ajay.cabXpress.service;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.repository.CabRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.BookingTransformer;
import com.ajay.cabXpress.transformer.CabTransformer;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabService {

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    public CabResponse registerCab(CabRequest cabRequest) {
        Driver driver = driverRepository.findByEmail(cabRequest.getDriverEmail());

        if(driver == null)
            throw new DriverNotFoundException("Driver with given email not register with us");

        driver.setCab(CabTransformer.cabRequestToCab(cabRequest));
        driver.getCab().setDriver(driver);
        driver.getCab().setAvailability(true);
        driverRepository.save(driver);
        //cabRepository.save(driver.getCab());     //no need as we cascaded cab in driver, so auto reflect

        return CabTransformer.cabToCabResponse(driver.getCab());
    }

    public CabResponse makeCabUnavailable(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        currCab.setAvailability(false);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also

        return CabTransformer.cabToCabResponse(savedDriver.getCab());
    }

    public CabResponse changeFarePerKm(String cabNo, double newFarePerKm) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        currCab.setFarePerKm(newFarePerKm);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also

        return CabTransformer.cabToCabResponse(savedDriver.getCab());
    }

    public List<BookingResponse> getAllBookingOfGivenCabNo(String cabNo) {
        List<Booking> booking = bookingRepository.getAllBookingOfGivenCabNo(cabNo);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings: booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }

        return response;
    }

    public List<BookingResponse> getLastNBookingOfGivenCabNo(String cabNo, int n) {
        List<Booking> booking = bookingRepository.getLastNBookingOfGivenCabNo(cabNo, n);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings: booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }

        return response;
    }

    public CabResponse endTrip(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        currCab.setAvailability(true);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also

        return CabTransformer.cabToCabResponse(savedDriver.getCab());
    }

    public CabResponse makeCabAvailable(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        currCab.setAvailability(true);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also

        return CabTransformer.cabToCabResponse(savedDriver.getCab());
    }
}
