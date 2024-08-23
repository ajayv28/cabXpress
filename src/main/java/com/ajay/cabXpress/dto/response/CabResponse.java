package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.CabType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CabResponse {

    String cabNo;

    CabType cabType;

    double farePerKm;

    DriverResponse driverResponse;
}
