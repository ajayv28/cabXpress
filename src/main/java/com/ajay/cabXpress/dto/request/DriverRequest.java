package com.ajay.cabXpress.dto.request;

import com.ajay.cabXpress.Enum.Gender;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverRequest {

    long mobileNumber;

    String email;

    String password;

    String name;

    String age;

    Gender gender;

    Date dob;
}
