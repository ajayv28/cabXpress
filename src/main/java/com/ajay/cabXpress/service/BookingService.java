package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.BookingStatus;
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

        return BookingTransformer.bookingToBookingResponse(savedBooking);




    }
}
