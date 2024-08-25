package com.ajay.cabXpress.transformer;


import com.ajay.cabXpress.dto.request.BookingRequest;
import com.ajay.cabXpress.dto.response.BookingResponse;
import com.ajay.cabXpress.model.Booking;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest){
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .totalDistance(bookingRequest.getTotalDistance())           // we are getting total dist from customer as we dont use gmaps remote API
                .cabType(bookingRequest.getCabType())
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking){
        return BookingResponse.builder()
                .bookingId(booking.getBookingId())
                .bookingStatus(booking.getBookingStatus())
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .totalDistance(booking.getTotalDistance())
                .totalFare(booking.getTotalFare())
                .driverResponse(DriverTransformer.driverToDriverResponse(booking.getDriver()))
                .cabType(booking.getCabType())
                .build();
    }
}