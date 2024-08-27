package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.Enum.CabType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String bookingId;

    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;

    String pickup;

    String destination;

    double totalDistance;

    String couponCode;

    double totalFare;

    @Enumerated(EnumType.STRING)
    CabType cabType;


    @CreationTimestamp
    Date bookingDateAndTime;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Driver driver;

}
