package com.ajay.cabXpress.service;

import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.CustomerRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendHBDMail(Customer customer) {
        String text = "Dear Mr./Mrs. " + customer.getName() + ", Wishing you a very Happy Birthday! We hope your day is filled with joy, laughter, and all the things that make you happiest. We truly value you as a customer and expecting for a long term partnership";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("Happy Birthday, Mr./Mrs. " + customer.getName());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendHBDMail(Driver driver) {
        String text = "Dear Mr./Mrs. " + driver.getName() + ", Wishing you a very Happy Birthday! We hope your day is filled with joy, laughter, and all the things that make you happiest. We truly value you as our driving partner and expecting for a long term partnership";


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(driver.getEmail());
        simpleMailMessage.setSubject("Happy Birthday, Mr./Mrs. " + driver.getName());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendAnniversaryMail(Customer customer) {
        String text = "Dear Mr./Mrs. " + customer.getName() + ", Today marks a special milestone — it’s the anniversary of your registration with us! We want to take this moment to thank you for your continued support and loyalty.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setSubject("Happy Anniversary, Mr./Mrs. " + customer.getName());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public void sendAnniversaryMail(Driver driver) {
        String text = "Dear Mr./Mrs. " + driver.getName() + ", Today marks a special milestone — it’s the anniversary of your registration with us! We want to take this moment to thank you for your continued support and loyalty.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.cabxpress@gmail.com");
        simpleMailMessage.setTo(driver.getEmail());
        simpleMailMessage.setSubject("Happy Anniversary, Mr./Mrs. " + driver.getName());
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }

    public String adminDailyTask() {
        Date currDate = Date.valueOf(LocalDate.now());
        List<Customer> bdayCustomers = customerRepository.getAllCustomerWithTodayBirthday(currDate);
        List<Customer> anniversaryCustomer = customerRepository.getAllCustomerWithTodayAnniversary(currDate);
        List<Driver> bdayDrivers = driverRepository.getAllDriverWithTodayBirthday(currDate);
        List<Driver> anniversaryDrivers = driverRepository.getAllDriverWithTodayAnniversary(currDate);

        for(Customer customer:bdayCustomers){
            sendHBDMail(customer);
        }

        for(Customer customer:anniversaryCustomer){
            sendAnniversaryMail(customer);
        }

        for(Driver driver:bdayDrivers){
            sendHBDMail(driver);
        }

        for(Driver driver:anniversaryDrivers){
            sendAnniversaryMail(driver);
        }

        return "Successfully executed all daily task";
    }
}
