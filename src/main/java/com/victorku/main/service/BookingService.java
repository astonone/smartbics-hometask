package com.victorku.main.service;

import com.victorku.main.model.Booking;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface BookingService {

    Booking getBookingById(Long bookingId);

    Booking bookingRequest(String companyWorkTime, LocalDateTime requestDate, String employee, LocalDateTime bookingDate, Long bookingTime);

    Booking create(String companyWorkTime, LocalDateTime requestDate, String employee, LocalDateTime bookingDate, Long bookingTime);

    List<Booking> getBookingsByDate(LocalDateTime start, LocalDateTime end);
}
