package com.ajay.cabXpress.service;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.exception.DriverNotFoundException;
import com.ajay.cabXpress.model.Driver;
import com.ajay.cabXpress.repository.CabRepository;
import com.ajay.cabXpress.repository.DriverRepository;
import com.ajay.cabXpress.transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabService {

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest) {
        Driver driver = driverRepository.findByMobileNumber(cabRequest.getDriverMobile());

        if(driver == null)
            throw new DriverNotFoundException("Driver with given mobile number not register with us");

        driver.setCab(CabTransformer.cabRequestToCab(cabRequest));
        driver.getCab().setAvailability(true);
        driverRepository.save(driver);
        //cabRepository.save()     no need as we cascaded cab in driver, so auto reflect

        return CabTransformer.cabToCabResponse(driver.getCab());
    }
}
