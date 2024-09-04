package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Driver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DriverTransformer {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Driver driverRequestToDriver(DriverRequest driverRequest){
        return Driver.builder()
                .mobileNumber(driverRequest.getMobileNumber())
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .gender(driverRequest.getGender())
                .email(driverRequest.getEmail())
                .dob(driverRequest.getDob())
                .password(passwordEncoder.encode(driverRequest.getPassword()))
                .role("ROLE_DRIVER")
                .build();
    }

    public static DriverResponse driverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .mobileNumber(driver.getMobileNumber())
                .email(driver.getEmail())
                .name(driver.getName())
                .age(driver.getAge())
                .dob(driver.getDob())
                .gender(driver.getGender())
                .build();
    }
}
