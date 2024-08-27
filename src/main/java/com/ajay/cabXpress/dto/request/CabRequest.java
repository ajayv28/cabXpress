package com.ajay.cabXpress.dto.request;

import com.ajay.cabXpress.Enum.CabType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CabRequest {

    String cabNo;

    CabType cabType;

    double farePerKm;

    String driverEmail;
}
