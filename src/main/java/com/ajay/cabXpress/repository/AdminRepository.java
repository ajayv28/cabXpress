package com.ajay.cabXpress.repository;

import com.ajay.cabXpress.model.Admin;
import com.ajay.cabXpress.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByEmail(String email);
}
