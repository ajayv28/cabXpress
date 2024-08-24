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

    UUID bookingId;

    BookingStatus bookingStatus;

    String pickup;

    String destination;

    double totalDistance;

    double totalFare;

    CabType cabType;

    DriverResponse driverResponse;          //no need cabResponse, as this driverResponse itself will return cabResponse

}
