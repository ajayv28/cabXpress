package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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

    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID bookingId;

    BookingStatus bookingStatus;

    String pickup;

    String destination;

    String totalDistance;

    String totalFare;

    Date bookingDateAndTime;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;

}
