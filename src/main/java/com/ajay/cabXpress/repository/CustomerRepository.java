package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.model.Customer;
import com.ajay.cabXpress.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value="select * from customer where mobile_number =:mobileNumber", nativeQuery=true)
    public Customer findByMobileNumber(long mobileNumber);

    public Customer findByCurrentAllocatedBookingId(String bookingId);

    public Customer findByEmail(String email);
    
    @Query(value="select * from customer where registered_on >=:date", nativeQuery=true)
    public List<Customer> getAllCustomerRegisteredAfterSpecificDate(Date date);
    
    @Query(value="select * from customer where age >=:n", nativeQuery=true)
    public List<Customer> getAllCustomerByAgeAboveN(int n);

    public List<Customer> findByGender(Gender gender);

    @Query(value="select * from customer where gender =:gender and age <=:n", nativeQuery=true)
    public List<Customer> getAllCustomerByGenderAgeBelowN(String gender, int n);

    @Query(value ="select * from customer where id in(select customer_id from booking group by customer_id having count(*) >=:n)", nativeQuery = true)
    public List<Customer> getAllCustomerWithMoreThanNBooking(int n);

    @Query(value ="select * from customer where month(dob) = month(:todayDate) and day(dob) = day(:todayDate)", nativeQuery = true)
    public List<Customer> getAllCustomerWithTodayBirthday(Date todayDate);

    @Query(value ="select * from customer where month(registeredOn) = month(:todayDate) and day(dob) = day(:todayDate)", nativeQuery = true)
    public List<Customer> getAllCustomerWithTodayAnniversary(Date todayDate);
}
