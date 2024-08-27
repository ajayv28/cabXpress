package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.Enum.CabType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookingResponse {

    String bookingId;

    BookingStatus bookingStatus;

    String pickup;

    String destination;

    double totalDistance;

    double totalFare;

    String couponCode;

    DriverResponse driverResponse;

    CabResponse cabResponse;   //need cabResponse, as this driverResponse itself will not return cabResponse

}
