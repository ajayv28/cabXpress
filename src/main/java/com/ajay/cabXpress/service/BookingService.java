package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.exception.*;
import com.ajay.cabXpress.model.*;
import com.ajay.cabXpress.repository.*;
import com.ajay.cabXpress.transformer.BookingTransformer;
import com.ajay.cabXpress.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ObjectUtils;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMailToCustomer (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getCustomer().getName() + ", your booking with cabXpress is confirmed. Cab number - " + savedBooking.getDriver().getCab().getCabNo() + " is on the way to pick you up. Your booking ID is: " + savedBooking.getBookingId() + ". Our Driver " + savedBooking.getDriver().getName() + " is the captain of your trip. Mobile Number of the allocated driver is " + savedBooking.getDriver().getMobileNumber() + ". Thanks for booking with us. Wishing you a safe and happy journey. Kindly call our 24x7 support hotline for any assistance.";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getCustomer().getEmail());
        simpleMailMessage.setSubject("cabXpress - Booking Confirmation");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }


    public void sendMailToDriver (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getDriver().getName() + ", you have a new booking for your Cab number - " + savedBooking.getDriver().getCab().getCabNo() + ". You can pickup the customer from " + savedBooking.getPickup() + " and drop them at " +  savedBooking.getDestination() + ". You can collect a total fare of Rs." + savedBooking.getTotalFare() + " . Incase of any assistance needed with route of pickup location, kindly contact the customer through mobile at " + savedBooking.getDriver().getMobileNumber() + ". Booking ID is: " + savedBooking.getBookingId() +". Kindly maintain the professionalism throughout the ride to ensure highest satisfaction of the guest.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getDriver().getEmail());
        simpleMailMessage.setSubject("cabXpress - New Booking Received for your Cab");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Customer currCustomer = customerRepository.findByEmail(bookingRequest.getCustomerEmail());

        if(currCustomer==null)
            throw new CustomerNotFoundException("Customer with given email id does not exist");

        if(currCustomer.isCustomerFreeCurrently()==false)
            throw new CustomerEngagedWithAnotherBookingException("Already another unfinished booking exist for this customer");

        Cab availableCab = cabRepository.getRandomAvailableCab(bookingRequest.getCabType().toString());

        if(ObjectUtils.isEmpty(availableCab))
            throw new CabNotAvailableException("Sorry currently all cabs are busy");

        Booking newBooking = BookingTransformer.bookingRequestToBooking(bookingRequest);
        newBooking.setBookingStatus(BookingStatus.CONFIRMED);
        double estimatedFare = (newBooking.getTotalDistance()*availableCab.getFarePerKm());

        if(bookingRequest.getCouponCode() != null) {
            Coupon appliedCoupon = couponRepository.findByCouponCode(bookingRequest.getCouponCode());


            if (ObjectUtils.isEmpty(appliedCoupon))
                throw new CouponNotFoundException("Sorry no coupon exist with such code");

            if(appliedCoupon.getQuantity()==0)
                throw new CouponNotFoundException("Sorry coupon expired");

            appliedCoupon.setQuantity(appliedCoupon.getQuantity()-1);
            couponRepository.save(appliedCoupon);

            double offerPercentage = appliedCoupon.getFlatOfferPercentage();

            newBooking.setTotalFare(estimatedFare - (offerPercentage * estimatedFare) / 100);
        }else{
            newBooking.setTotalFare(estimatedFare);
        }


        Driver currDriver = availableCab.getDriver();
        newBooking.setDriver(currDriver);
        newBooking.setCustomer(currCustomer);
        currCustomer.setCustomerFreeCurrently(false);

        Booking savedBooking = bookingRepository.save(newBooking);

        availableCab.setAvailability(false);
        currDriver.getBookings().add(savedBooking);
        currCustomer.getBookings().add(savedBooking);

        currCustomer.setCurrentAllocatedBookingId(savedBooking.getBookingId());
        currDriver.setCurrentAllocatedBookingId(savedBooking.getBookingId());

        driverRepository.save(currDriver);        // cab & booking gets saved as driver has cancading of both
        customerRepository.save(currCustomer);   // booking also will be saved as customer has cascading of booking

        //sendMailToCustomer(savedBooking);
        //sendMailToDriver(savedBooking);
        
        return BookingTransformer.bookingToBookingResponse(savedBooking);



    }

    public List<BookingResponse> getLastNBooking(int n) {

        List<Booking> booking = bookingRepository.getLastNBooking(n);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }

    public List<BookingResponse> getBookingWithTotalFareGreaterThanX(float x) {

        List<Booking> booking = bookingRepository.getBookingWithTotalFareGreaterThanX(x);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }

    public BookingResponse getBookingDetailWithBookingId(String bookingId) {
        Booking booking = bookingRepository.getBookingDetailWithBookingId(bookingId);
        return BookingTransformer.bookingToBookingResponse(booking);
    }

    public List<BookingResponse> getAllCancelledBooking() {

        List<Booking> booking = bookingRepository.getAllCancelledBooking();
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }

    public List<BookingResponse> getBookingWithTotalDistanceGreaterThanX(double x) {

        List<Booking> booking = bookingRepository.getBookingWithTotalDistanceGreaterThanX(x);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }

    public List<BookingResponse> getBookingWithGivenCabType(CabType cabType) {

        List<Booking> booking = bookingRepository.getBookingWithGivenCabType(cabType.toString());
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }

    public List<BookingResponse> getBookingAfterGivenDate(Date date) {
        List<Booking> booking = bookingRepository.getBookingAfterGivenDate(date);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings:booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }
        return response;
    }

    public List<BookingResponse> getBookingBetweenGivenDates(Date fromDate, Date toDate) {
        List<Booking> booking = bookingRepository.getBookingBetweenGivenDates(fromDate, toDate);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings:booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }
        return response;
    }

    public List<BookingResponse> getAllActiveRides() {
        List<Booking> booking = bookingRepository.getAllActiveRides();
        List<BookingResponse> response = new ArrayList<>();

        for(Booking bookings:booking){
            response.add(BookingTransformer.bookingToBookingResponse(bookings));
        }
        return response;
    }

    public List<BookingResponse> getAllBookingByRating(int rating) {
        List<Booking> bookings = bookingRepository.getAllBookingByRating(rating);
        List<BookingResponse>  response = new ArrayList<>();

        for(Booking booking : bookings){
            response.add(BookingTransformer.bookingToBookingResponse(booking));
        }

        return response;
    }

    public BookingResponse cancelRide(String customerEmail) {
        Customer currCustomer = customerRepository.findByEmail(customerEmail);
        if(currCustomer.getCurrentAllocatedBookingId().length()==0){
            throw new NoOngoingBookingFoundException("Currently you have no ongoing booking");
        }

        Booking currBooking = bookingRepository.findByBookingId(currCustomer.getCurrentAllocatedBookingId());
        Driver currDriver = currBooking.getDriver();
        Cab currCab = currDriver.getCab();

        currBooking.setBookingStatus(BookingStatus.CANCELLED);

        currCustomer.setCustomerFreeCurrently(true);
        currCustomer.setCurrentAllocatedBookingId("");

        currDriver.setCurrentAllocatedBookingId("");

        currCab.setAvailability(true);

    //cascading automatically updates the changes in booking entity in our List<Booking> at Driver and Customer entity

        Booking savedBooking = bookingRepository.save(currBooking);
        driverRepository.save(currDriver);
        customerRepository.save(currCustomer);

        return BookingTransformer.bookingToBookingResponse(savedBooking);
    }
}
