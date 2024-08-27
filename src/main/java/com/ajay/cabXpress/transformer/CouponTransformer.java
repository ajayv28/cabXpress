package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.CouponRequest;
import com.ajay.cabXpress.dto.response.CouponResponse;
import com.ajay.cabXpress.model.Coupon;

import java.util.UUID;

public class CouponTransformer {

    public static Coupon couponRequestToCoupon(CouponRequest couponRequest){
        return Coupon.builder()
                .flatOfferPercentage(couponRequest.getFlatOfferPercentage())
                .couponCode(String.valueOf(UUID.randomUUID()))
                .build();
    }

    public static CouponResponse couponToCouponResponse(Coupon coupon){
        return CouponResponse.builder()
                .flatOfferPercentage(coupon.getFlatOfferPercentage())
                .couponCode(coupon.getCouponCode())
                .build();
    }
}
