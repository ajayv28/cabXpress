package com.ajay.cabXpress.dto.request;

import com.ajay.cabXpress.Enum.Gender;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    long mobileNumber;

    String email;

    String name;

    String age;

    Gender gender;

}
