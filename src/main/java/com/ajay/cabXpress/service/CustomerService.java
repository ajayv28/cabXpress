package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.exception.RatingGivenAlreadyForThisRide;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.BookingRepository;
import com.ajay.cabXpress.repository.CustomerRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.BookingTransformer;
import com.ajay.cabXpress.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DriverRepository driverRepository;

    public CustomerResponse registerCustomer(CustomerRequest customerRequest) {

        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomerRegisteredAfterSpecificDate(Date date) {
        List<Customer> customer = customerRepository.getAllCustomerRegisteredAfterSpecificDate(date);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }

    public List<CustomerResponse> getAllCustomerWithMoreThanNBooking(int n) {
        List<Customer> customer = customerRepository.getAllCustomerWithMoreThanNBooking(n);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }

    public List<CustomerResponse> getAllCustomerByAgeAboveN(int age) {
        List<Customer> customer = customerRepository.getAllCustomerByAgeAboveN(age);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }

    public List<CustomerResponse> getAllCustomerByGender(Gender gender) {
        List<Customer> customer = customerRepository.findByGender(gender);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }

    public List<CustomerResponse> getAllCustomerByGenderAndAgeBelowN(Gender gender, int age) {
        List<Customer> customer = customerRepository.getAllCustomerByGenderAgeBelowN(gender.toString(), age);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }

    public List<BookingResponse> getAllBookingOfCurrentCustomer(String email) {
        int customerId = customerRepository.findByEmail(email).getId();
        List<Booking> bookings = bookingRepository.getAllBookingOfCurrentCustomer(customerId);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking booking:bookings){
            response.add(BookingTransformer.bookingToBookingResponse(booking));
        }
        return response;
    }

    public List<BookingResponse> getLastNBookingOfCurrentCustomer(String email, int n) {
        int customerId = customerRepository.findByEmail(email).getId();
        List<Booking> bookings = bookingRepository.getLastNBookingOfCurrentCustomer(customerId, n);
        List<BookingResponse> response = new ArrayList<>();

        for(Booking booking:bookings){
            response.add(BookingTransformer.bookingToBookingResponse(booking));
        }
        return response;
    }

    public String deleteCustomer(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        customerRepository.delete(customer);
        return "Successfully deleted from our database";
    }

    public String rateDriverOfLastCompletedTrip(String customerEmail, int rating) {
        Customer currCustomer = customerRepository.findByEmail(customerEmail);
        int customerId = currCustomer.getId();
        Booking lastBookingCompletedSuccessfully = bookingRepository.lastBookingCompletedSuccessfully(customerId);

        if(lastBookingCompletedSuccessfully.getTripRating() != 0)
            throw new RatingGivenAlreadyForThisRide("You have already rated this ride");

        lastBookingCompletedSuccessfully.setTripRating(rating);

        lastBookingCompletedSuccessfully.getDriver().setRatingSum(lastBookingCompletedSuccessfully.getDriver().getRatingSum()+rating);
        lastBookingCompletedSuccessfully.getDriver().setRatingCount(lastBookingCompletedSuccessfully.getDriver().getRatingCount()+1);

        Driver currDriver = lastBookingCompletedSuccessfully.getDriver();
        driverRepository.save(currDriver);
        bookingRepository.save(lastBookingCompletedSuccessfully);

        return "Successfully rated your last trip";
    }
}
