package com.victorku.main.repository;

import com.victorku.main.model.Booking;
import org.joda.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByBookingDateBetween(LocalDateTime start, LocalDateTime end);
}
