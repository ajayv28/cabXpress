package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findByMobileNumber(long mobileNumber);

    public Driver findByEmail(String email);

    @Query(value="select * from driver where age >=:n", nativeQuery=true)
    public List<Driver> getAllDriverByAgeAboveN(int n);

    @Query(value="select * from driver where gender =:gender", nativeQuery=true)
    public List<Driver> getAllDriverByGender(String gender);

    @Query(value="select * from driver where gender =:gender and age <=:n", nativeQuery=true)
    public List<Driver> getAllDriverByGenderAndAgeBelowN(String gender, int n);

    
    @Query(value="select * from driver where id in (select driver_id from cab where driver_id IS NOT null)", nativeQuery=true)
    public List<Driver> getAllDriverWithCabRegistered();

    @Query(value="select * from driver where id not in (select driver_id from cab where driver_id IS NOT null)", nativeQuery=true)
    public List<Driver> getAllDriverWithNoCabRegistered();

    @Query(value="select * from driver where id in(select driver_id from booking group by driver_id having count(*) >=:n)", nativeQuery=true)
    public List<Driver> getDriverWithMoreThanNBooking(int n);

    @Query(value ="select * from driver where month(dob) = month(:todayDate) and day(dob) = day(:todayDate)", nativeQuery = true)
    public List<Driver> getAllDriverWithTodayBirthday(Date todayDate);

    @Query(value ="select * from driver where month(registeredOn) = month(:todayDate) and day(dob) = day(:todayDate)", nativeQuery = true)
    public List<Driver> getAllDriverWithTodayAnniversary(Date todayDate);

    @Query(value="select * from driver where (rating_sum/rating_count) =:rating", nativeQuery=true)
    public List<Driver> getAllDriverByRating(int rating);
}
