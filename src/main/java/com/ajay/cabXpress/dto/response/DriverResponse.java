package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

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

    Date dob;

}
