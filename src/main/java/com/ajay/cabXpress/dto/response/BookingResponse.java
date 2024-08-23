package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.BookingStatus;
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

    String totalDistance;

    String totalFare;

    DriverResponse driverResponse;          //no need cabResponse, as this driverResponse itself will return cabResponse

}
