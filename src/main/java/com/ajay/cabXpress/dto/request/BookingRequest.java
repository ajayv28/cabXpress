package com.ajay.cabXpress.dto.request;

import jakarta.persistence.GeneratedValue;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingRequest {

    String pickup;

    String destination;

    String totalDistance;

    String customerEmail;                 // as we are not using loggedIn details to book
}
