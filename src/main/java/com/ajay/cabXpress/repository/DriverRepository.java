package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

    public Driver findByMobileNumber(long mobileNumber);

}
