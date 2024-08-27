package com.ajay.cabXpress.dto.request;

import com.ajay.cabXpress.Enum.CabType;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {

    String pickup;

    String destination;

    double totalDistance;

    String couponCode;

    CabType cabType;

    String customerEmail;                 // as we are not using loggedIn details to book
}
