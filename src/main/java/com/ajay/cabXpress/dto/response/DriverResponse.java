package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DriverResponse {

    long mobileNumber;

    String email;

    String name;

    String age;

    Gender gender;

}
