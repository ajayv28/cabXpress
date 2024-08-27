package com.ajay.cabXpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ajay.cabXpress.model.Coupon;

import java.util.UUID;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    public Coupon findByCouponCode(String couponCode);
}
