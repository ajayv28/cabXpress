package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findByMobileNumber(long mobileNumber);

    @Query(value = "select * from driver where bookings.size() >= :n", nativeQuery=true)
    public List<Driver> getDriverWithMoreThanNBooking(int n);

    @Query(value="select * from driver where age >=:n", nativeQuery=true)
    public List<Driver> getAllDriverByAgeAboveN(int n);

    @Query(value="select * from driver where gender =:gender", nativeQuery=true)
    public List<Driver> getAllDriverByGender(Gender gender);

    @Query(value="select * from driver where gender =:gender and age <=:n", nativeQuery=true)
    public List<Driver> getAllDriverByGenderAndAgeBelowN(Gender gender, int n);

    @Query(value="select * from driver where cab <> null", nativeQuery=true)
    public List<Driver> getAllDriverWithCabRegistered();

    @Query(value="select * from driver where cab = null", nativeQuery=true)
    public List<Driver> getAllDriverWithNoCabRegistered();

}
