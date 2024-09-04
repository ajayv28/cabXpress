package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.exception.CabAlreadyRegisteredException;
import com.ajay.cabXpress.exception.CabNotAvailableException;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.exception.NoOngoingBookingFoundException;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.repository.CabRepository;
import com.ajay.cabXpress.repository.CustomerRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.BookingTransformer;
import com.ajay.cabXpress.transformer.CabTransformer;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    BookingService bookingService;

    public void sendEndOfTripMailToCustomer (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getCustomer().getName() + ", your ride with cabXpress in Cab number - " + savedBooking.getDriver().getCab().getCabNo() + " for booking ID: " + savedBooking.getBookingId() + "with Driver " + savedBooking.getDriver().getName() + " is ended. Thanks for booking with us. Kindly pay the amount of Rs. " + savedBooking.getTotalFare() + " by cash. Kindly call our 24x7 support hotline for any assistance.";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getCustomer().getEmail());
        simpleMailMessage.setSubject("cabXpress - Trip ended");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }


    public void sendEndOfTripMailToDriver (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getDriver().getName() + ", your ride for your Cab number - " + savedBooking.getDriver().getCab().getCabNo() + " from " + savedBooking.getPickup() + " to " +  savedBooking.getDestination() + "is ended. You can collect a total fare of Rs." + savedBooking.getTotalFare() + ". Thanks for maintaining the professionalism throughout the ride to ensure highest satisfaction of the guest.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getDriver().getEmail());
        simpleMailMessage.setSubject("cabXpress - Trip ended");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }


    public void sendDriverCancelledTripMailToCustomer (Customer customer) {
        String text = "Dear Mr./Mrs. " + customer.getName() + ", your booking with cabXpress is CANCELLED. Please try to rebook. We are extremely sorry for the inconvinience caused. Kindly call our 24x7 support hotline for any assistance.";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("cabXpress - Booking CANCELLED");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public CabResponse registerCab(CabRequest cabRequest, String driverEmail) {
        Driver driver = driverRepository.findByEmail(driverEmail);

        if(driver == null)
            throw new DriverNotFoundException("Driver not register with us with current logged in credentials");

        if(driver.getCab() != null)
            throw new CabAlreadyRegisteredException("Cab alread registered for this Driver Account");

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

        if(currCab.isAvailability()==false){
            Customer currCustomer = customerRepository.findByCurrentAllocatedBookingId(currDriver.getCurrentAllocatedBookingId());
            bookingService.cancelRide(currCustomer.getEmail());
            sendDriverCancelledTripMailToCustomer(currCustomer);
        }

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

    public BookingResponse endTrip(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        String bookingId = currDriver.getCurrentAllocatedBookingId();

        if(bookingId.length()==0)
            throw new NoOngoingBookingFoundException("No trip ongoing for you to end");

        currCab.setAvailability(true);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also



        Booking currBooking = bookingRepository.findByBookingId(bookingId);
        currBooking.setBookingStatus(BookingStatus.COMPLETED);
        Booking savedBooking = bookingRepository.save(currBooking);

        Customer currCustomer = savedBooking.getCustomer();
        currDriver.setCurrentAllocatedBookingId("");
        currCustomer.setCurrentAllocatedBookingId("");
        currCustomer.setCustomerFreeCurrently(true);

        driverRepository.save(currDriver);
        customerRepository.save(currCustomer);
        //sendEndOfTripMailToDriver(savedBooking);
        //sendEndOfTripMailToCustomer(savedBooking);

        return BookingTransformer.bookingToBookingResponse(savedBooking);
    }

    public CabResponse makeCabAvailable(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();

        currCab.setAvailability(true);
        Driver savedDriver = driverRepository.save(currDriver);     //this will save in cab also

        return CabTransformer.cabToCabResponse(savedDriver.getCab());
    }

    public BookingResponse startTrip(String cabNo) {
        Cab currCab = cabRepository.findByCabNo(cabNo);
        Driver currDriver = currCab.getDriver();
        String bookingId = currDriver.getCurrentAllocatedBookingId();

        if(bookingId.length()==0)
            throw new NoOngoingBookingFoundException("No trip allocated for you to start");

        Booking currBooking = bookingRepository.findByBookingId(bookingId);
        currBooking.setBookingStatus(BookingStatus.ONGOING);
        Booking savedBooking = bookingRepository.save(currBooking);


        return BookingTransformer.bookingToBookingResponse(savedBooking);
    }

    public String deleteCab(String cabNo) {
        Cab cab = cabRepository.findByCabNo(cabNo);
        cabRepository.delete(cab);
        return "Successfully deleted from our database";
    }
}
