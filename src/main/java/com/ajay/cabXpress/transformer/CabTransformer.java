package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.model.Cab;

public class CabTransformer {

    public static Cab cabRequestToCab(CabRequest cabRequest){
        return Cab.builder()
                .cabNo(cabRequest.getCabNo())
                .cabType(cabRequest.getCabType())
                .farePerKm(cabRequest.getFarPerKm())
                //.driver(cabRequest.getDriverMobile())   take care of allocating driver in controller layer
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab){
        return CabResponse.builder()
                .cabNo(cab.getCabNo())
                .cabType(cab.getCabType())
                .farePerKm(cab.getFarePerKm())
                .driverResponse(DriverTransformer.driverToDriverResponse(cab.getDriver()))
                .build();
    }
}
