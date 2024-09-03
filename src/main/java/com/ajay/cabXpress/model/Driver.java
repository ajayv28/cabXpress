package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.print.Book;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    long mobileNumber;

    @Column(unique = true, nullable = false)
    String email;

    String password;

    String role;

    String name;

    int ratingSum;
    int ratingCount;

    String age;

    Date dob;

    String currentAllocatedBookingId;

    @CreationTimestamp
    Date registeredOn;


    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    Cab cab;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Booking> bookings = new ArrayList<>();

}
