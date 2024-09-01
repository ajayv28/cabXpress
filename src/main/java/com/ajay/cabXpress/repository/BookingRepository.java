package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.CabType;
import com.ajay.cabXpress.model.Booking;
import com.ajay.cabXpress.model.Driver;
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

    @Query(value="select * from booking where booking_id =:bookingId", nativeQuery=true)
    public Booking getBookingDetailWithBookingId(String bookingId);

    @Query(value="select * from booking where booking_status = 'CANCELLED'", nativeQuery=true)
    public List<Booking> getAllCancelledBooking();

    @Query(value="select * from booking where total_distance >=:x", nativeQuery=true)
    public List<Booking> getBookingWithTotalDistanceGreaterThanX(double x);

    @Query(value="select * from booking where cab_type =:cabType", nativeQuery=true)
    public List<Booking> getBookingWithGivenCabType(String cabType);

    @Query(value="select * from booking where booking_date_and_time >=:date", nativeQuery=true)
    public List<Booking> getBookingAfterGivenDate(Date date);
    // need to convert util to sql format

    @Query(value="select * from booking where booking_date_and_time between :fromDate and :toDate", nativeQuery=true)
    public List<Booking> getBookingBetweenGivenDates(Date fromDate, Date toDate);
    // need to convert util to sql format



    @Query(value="select * from booking where driver_id =:driver_id", nativeQuery=true)
    public List<Booking> getAllBookingOfCurrentDriver(int driver_id);

    @Query(value="select * from booking where driver_id =:driver_id order by id desc limit :n", nativeQuery=true)
    public List<Booking> getLastNBookingOfCurrentDriver(int driver_id, int n);


    @Query(value="select * from booking where customer_id =:customer_id", nativeQuery=true)
    public List<Booking> getAllBookingOfCurrentCustomer(int customer_id);

    @Query(value="select * from booking where customer_id =:customer_id order by id desc limit :n", nativeQuery=true)
    public List<Booking> getLastNBookingOfCurrentCustomer(int customer_id, int n);


    @Query(value="select * from booking where driver_id in (select driver_id from cab where cab_no =:cabNo)", nativeQuery = true)
    public List<Booking> getAllBookingOfGivenCabNo(String cabNo);
    
    @Query(value="select * from booking where driver_id in (select driver_id from cab where cab_no =:cabNo) order by id desc limit :n ", nativeQuery = true)
    public List<Booking> getLastNBookingOfGivenCabNo(String cabNo, int n);
}
