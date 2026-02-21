package com.prathamesh.AirBnb.service.interfaces;

import com.prathamesh.AirBnb.dto.BookingDTO;
import com.prathamesh.AirBnb.dto.BookingRequest;
import com.prathamesh.AirBnb.dto.GuestDTO;
import com.prathamesh.AirBnb.enums.BookingStatus;
import com.stripe.model.Event;

import java.time.LocalDate;
import java.util.List;


public interface BookingService {
    BookingDTO initialiseBooking(BookingRequest bookingRequest);

    BookingDTO addGuests(Long bookingId, List<GuestDTO> guestDtoList);

    void cancelBooking(Long bookingId);

    BookingStatus getBookingStatus(Long bookingId);

    List<BookingDTO> getAllBookingsByHotelId(Long hotelId);

    List<BookingDTO> getMyBookings();
}
