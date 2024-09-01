package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

    @Query(value = "select * from cab where availability = 1 and cab_type =:cabType order by rand() limit 1", nativeQuery = true)
    public Cab getRandomAvailableCab(String cabType);

    public Cab findByCabNo(String cabNo);
}
