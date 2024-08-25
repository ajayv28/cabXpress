package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.repository.CustomerRepository;
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

    public List<CustomerResponse> getCustomerWithMoreThanNBooking(int n) {
        List<Customer> customer = customerRepository.getCustomerWithMoreThanNBooking(n);
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
        List<Customer> customer = customerRepository.getAllCustomerByGenderAgeBelowN(gender, age);
        List<CustomerResponse> response = new ArrayList<>();

        for(Customer currCustomer: customer){
            response.add(CustomerTransformer.customerToCustomerResponse(currCustomer));
        }

        return response;
    }
}
