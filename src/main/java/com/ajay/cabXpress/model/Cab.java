package com.ajay.cabXpress.model;

import com.ajay.cabXpress.Enum.CabType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(unique = true, nullable = false)
    String cabNo;

    boolean availability;

    CabType cabType;

    double farePerKm;

    @OneToOne
    @JoinColumn
    Driver driver;

}
