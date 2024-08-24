package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.BookingStatus;
import com.ajay.cabXpress.Enum.CabType;
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

    UUID bookingId = UUID.randomUUID();

    BookingStatus bookingStatus;

    String pickup;

    String destination;

    double totalDistance;

    double totalFare;

    CabType cabType;

    @CreationTimestamp
    Date bookingDateAndTime;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;

}
