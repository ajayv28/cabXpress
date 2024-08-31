package com.ajay.cabXpress.config;

import com.ajay.cabXpress.config.UserDetailsCreator;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.CustomerRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return new UserDetailsCreator(customer);
        }

        Driver driver = driverRepository.findByEmail(email);
        if (driver != null) {
            return new UserDetailsCreator(driver);
        }

        throw new UsernameNotFoundException("Invalid email id");
    }
}
