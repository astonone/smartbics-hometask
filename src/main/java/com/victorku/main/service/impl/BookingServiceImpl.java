package com.victorku.main.service.impl;

import com.victorku.main.model.Booking;
import com.victorku.main.repository.BookingRepository;
import com.victorku.main.service.BookingService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findOne(bookingId);
    }

    @Override
    public Booking createBooking(String companyWorkTime, String employee, LocalDateTime bookingDate, Long bookingTime) {
        Booking booking = new Booking(companyWorkTime,employee,bookingDate,bookingTime);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDateTime start,LocalDateTime end) {
        List<Booking> bookingList = bookingRepository.findByBookingDateBetween(start,end);
        return bookingList;
    }
}
