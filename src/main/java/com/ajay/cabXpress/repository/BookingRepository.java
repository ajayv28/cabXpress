package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value="select * from booking order by id desc limit :n", nativeQuery=true)
    public List<Booking> getLastNBooking(int n);

    @Query(value="select * from booking where total_fare >=:x", nativeQuery=true)
    public List<Booking> getBookingWithTotalFareGreaterThanX(float x);

    @Query(value="select * from booking where bookingId =:booking_id", nativeQuery=true)
    public Booking getBookingDetailWithBookingId(UUID bookingId);

    @Query(value="select * from booking where booking_status = 'CANCELLED'", nativeQuery=true)
    public List<Booking> getAllCancelledBooking();

    @Query(value="select * from booking where total_distance >=:x", nativeQuery=true)
    public List<Booking> getBookingWithTotalDistanceGreaterThanX(double x);

    @Query(value="select * from booking where cab_type =:cabType", nativeQuery=true)
    public List<Booking> getBookingWithGivenCabType(CabType cabType);

    @Query(value="select * from booking where booking_date_and_time >=:date", nativeQuery=true)
    public List<Booking> getBookingAfterGivenDate(Date date);
    // need to convert util to sql format

    @Query(value="select * from booking where booking_date_and_time between :fromDate and :toDate", nativeQuery=true)
    public List<Booking> getBookingBetweenGivenDates(Date fromDate, Date toDate);
    // need to convert util to sql format
}
