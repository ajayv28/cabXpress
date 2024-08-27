package com.ajay.cabXpress.service;

import com.ajay.cabXpress.dto.request.CouponRequest;
import com.ajay.cabXpress.dto.response.CouponResponse;
import com.ajay.cabXpress.model.Coupon;
import com.ajay.cabXpress.repository.CouponRepository;
import com.ajay.cabXpress.transformer.CouponTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    public CouponResponse registerCoupon(CouponRequest couponRequest) {
        Coupon coupon = CouponTransformer.couponRequestToCoupon(couponRequest);
        Coupon savedCoupon = couponRepository.save(coupon);
        return CouponTransformer.couponToCouponResponse(savedCoupon);
    }
}
