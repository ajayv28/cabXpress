package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.Enum.Gender;
import com.ajay.cabXpress.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmail(String email);

   /* @Query(value="select * from booking where customer in (select * from customer where id =:id)", nativeQuery=true)
    public List<Booking> getAllBookingOfCurrentCustomer(int id)

    create customer id in booking 
    
    @Query(value="select * from customer where id in (select id from booking group by id having count(*) >= 5 )", nativeQuery=true)
    public List<Customer> getCustomerWithMoreThanNBooking(int n);
    
    @Query(value="", nativeQuery=true)
    public List<Customer> getLastNBookingOfCurrentCustomer(int id)
    create customer id in booking */
    
    @Query(value="select * from customer where registered_on >=:date", nativeQuery=true)
    public List<Customer> getAllCustomerRegisteredAfterSpecificDate(Date date);
    
    @Query(value="select * from customer where age >= :n", nativeQuery=true)
    public List<Customer> getAllCustomerByAgeAboveN(int n);

    public List<Customer> findByGender(Gender gender);

    @Query(value="select * from customer where gender =:gender and age <=:n", nativeQuery=true)
    public List<Customer> getAllCustomerByGenderAgeBelowN(Gender gender, int n);

    
}
