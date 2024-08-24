package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Driver;

public class DriverTransformer {

    public static Driver driverRequestToDriver(DriverRequest driverRequest){
        return Driver.builder()
                .mobileNumber(driverRequest.getMobileNumber())
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .gender(driverRequest.getGender())
                .email(driverRequest.getEmail())
                .build();
    }

    public static DriverResponse driverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .mobileNumber(driver.getMobileNumber())
                .email(driver.getEmail())
                .name(driver.getName())
                .age(driver.getAge())
                .gender(driver.getGender())
                .build();
    }
}
