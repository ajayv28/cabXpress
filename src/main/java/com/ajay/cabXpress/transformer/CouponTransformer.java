package com.ajay.cabXpress.transformer;

import com.ajay.cabXpress.dto.request.CouponRequest;
import com.ajay.cabXpress.dto.response.CouponResponse;
import com.ajay.cabXpress.model.Coupon;

public class CouponTransformer {

    public static Coupon couponRequestToCoupon(CouponRequest couponRequest){
        return Coupon.builder()
                .flatOfferPercentage(couponRequest.getFlatOfferPercentage())
                .build();
    }

    public static CouponResponse couponToCouponResponse(Coupon coupon){
        return CouponResponse.builder()
                .flatOfferPercentage(coupon.getFlatOfferPercentage())
                .build();
    }
}
