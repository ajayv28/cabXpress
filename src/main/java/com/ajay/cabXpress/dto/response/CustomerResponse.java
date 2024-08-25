package com.ajay.cabXpress.dto.response;

import com.ajay.cabXpress.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponse {

    long mobileNumber;

    String email;

    String name;

    String age;

    Gender gender;
}