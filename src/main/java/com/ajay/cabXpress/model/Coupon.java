package com.ajay.cabXpress.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String couponCode;

    double flatOfferPercentage;

}
