package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.exception.CabNotAvailableException;
import com.ajay.cabXpress.exception.CustomerNotFoundException;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Cab;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.repository.CabRepository;
import com.ajay.cabXpress.repository.CustomerRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    JavaMailSender javaMailSender;

    private void sendMailToCustomer (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getCustomer().getName() + ", your booking with cabXpress is confirmed. Cab number - " + savedBooking.getDriver().getCab().getCabNo() + " is on the way to pick you up. Your booking ID is: " + savedBooking.getBookingId() + ". Our Driver " + savedBooking.getDriver().getName() + " is the captain of your trip. Mobile Number of the allocated driver is " + savedBooking.getDriver().getMobileNumber() + ". Thanks for booking with us. Wishing you a safe and happy journey. Kindly call our 24x7 support hotline for any assistance";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getCustomer().getEmailId());
        simpleMailMessage.setSubject("cabXpress - Booking Confirmation");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }


        private void sendMailToDriver (Booking savedBooking) {
        String text = "Dear Mr./Mrs. " + savedBooking.getDriver().getName() + ", you have a new booking for your Cab number - " + savedBooking.getDriver().getCab().getCabNo() + ". You can pickup the customer from " + savedBooking.getPickup() + " and drop them at " +  savedBooking.getDestination() + ". You can collect a total fare of Rs." + savedBooking.getTotalFare() + "Incase of any assistance needed with route of pickup location, kindly contact the customer through mobile at" + savedBooking.getDriver().getMobileNumber() + ". Booking ID is: " + savedBooking.getBookingId() +". Kindly maintain the professionalism throughout the ride to ensure highest satisfaction of the guest";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(savedBooking.getDriver().getEmailId());
        simpleMailMessage.setSubject("cabXpress - New Booking Received for your Cab");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Customer currCustomer = customerRepository.findByEmail(bookingRequest.getCustomerEmail());
        if(currCustomer == null)
            throw new CustomerNotFoundException("Customer with given email id does not exist");

        Cab availableCab = cabRepository.getRandomAvailableCab();

        if(availableCab == null)
            throw new CabNotAvailableException("Sorry currently all cabs are busy");

        Booking newBooking = BookingTransformer.bookingRequestToBooking(bookingRequest);
        newBooking.setBookingStatus(BookingStatus.CONFIRMED);
        newBooking.setTotalFare(newBooking.getTotalDistance()*availableCab.getFarePerKm());


        Driver currDriver = availableCab.getDriver();
        newBooking.setDriver(currDriver);
        newBooking.setCustomer(currCustomer);

        Booking savedBooking = bookingRepository.save(newBooking);

        availableCab.setAvailability(false);
        currDriver.getBookings().add(savedBooking);
        currCustomer.getBookings().add(savedBooking);


        driverRepository.save(currDriver);        // cab & booking gets saved as driver has cancading of both
        customerRepository.save(currCustomer);   // booking also will be saved as customer has cascading of booking

        sendMailToCustomer(savedBooking);
        sendMailToDriver(savedBooking);
        
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

    public BookingResponse getBookingDetailWithBookingId(UUID bookingId) {
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

        List<Booking> booking = bookingRepository.getBookingWithGivenCabType(cabType);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking currBooking : booking){
            response.add(BookingTransformer.bookingToBookingResponse(currBooking));
        }

        return response;
    }
}
