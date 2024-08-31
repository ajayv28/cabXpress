package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.CustomerRequest;
import com.ajay.cabXpress.dto.response.CustomerResponse;
import com.ajay.cabXpress.model.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomerTransformer {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .mobileNumber(customerRequest.getMobileNumber())
                .age(customerRequest.getAge())
                .name(customerRequest.getName())
                .gender(customerRequest.getGender())
                .email(customerRequest.getEmail())
                .password(passwordEncoder.encode(customerRequest.getPassword()))
                .role("ROLE_CUSTOMER")
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .mobileNumber(customer.getMobileNumber())
                .age(customer.getAge())
                .gender(customer.getGender())
                .email(customer.getEmail())
                .name(customer.getName())
                .build();
    }
}
