package com.ajay.cabXpress.service;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse registerDriver(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }

    public List<DriverResponse> getAllDriverByAgeAboveN(int n) {
        List<Driver> driver = driverRepository.getAllDriverByAgeAboveN(n);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverByGender(Gender gender) {
        List<Driver> driver = driverRepository.getAllDriverByGender(gender);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverByGenderAndAgeBelowN(Gender gender, int n) {
        List<Driver> driver = driverRepository.getAllDriverByGenderAndAgeBelowN(gender, n);
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverWithCabRegistered() {
        List<Driver> driver = driverRepository.getAllDriverWithCabRegistered();
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }

    public List<DriverResponse> getAllDriverWithNoCabRegistered() {
        List<Driver> driver = driverRepository.getAllDriverWithNoCabRegistered();
        List<DriverResponse>  response = new ArrayList<>();

        for(Driver currDriver : driver){
            response.add(DriverTransformer.driverToDriverResponse(currDriver));
        }

        return response;
    }
}
