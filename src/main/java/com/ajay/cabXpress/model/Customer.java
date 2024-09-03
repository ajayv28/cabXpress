package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

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
public class Customer {

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

    String age;

    Date dob;

    String currentAllocatedBookingId;

    boolean customerFreeCurrently;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @CreationTimestamp
    Date registeredOn;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Booking> bookings = new ArrayList<>();



}
