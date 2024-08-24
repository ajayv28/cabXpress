package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
