package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.CabRequest;
import com.ajay.cabXpress.dto.response.CabResponse;
import com.ajay.cabXpress.model.Cab;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CabTransformer {

    public static Cab cabRequestToCab(CabRequest cabRequest){
        return Cab.builder()
                .cabNo(cabRequest.getCabNo())
                .cabType(cabRequest.getCabType())
                .farePerKm(cabRequest.getFarePerKm())
                //.driver(cabRequest.getDriverMobile())   take care of allocating driver in controller layer
                .build();
    }

    public static CabResponse cabToCabResponse(Cab cab){
        return CabResponse.builder()
                .cabNo(cab.getCabNo())
                .cabType(cab.getCabType())
                .farePerKm(cab.getFarePerKm())
                .build();
    }
}
