package com.ajay.cabXpress.service;

import com.ajay.cabXpress.dto.request.DriverRequest;
import com.ajay.cabXpress.dto.response.DriverResponse;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse registerDriver(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDriver);
    }
}
