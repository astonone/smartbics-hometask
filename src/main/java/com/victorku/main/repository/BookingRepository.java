package com.victorku.main.repository;

import com.victorku.main.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
