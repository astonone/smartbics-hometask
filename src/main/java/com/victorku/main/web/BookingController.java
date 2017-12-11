package com.victorku.main.web;

import com.victorku.main.model.Booking;
import com.victorku.main.web.model.BookingDTO;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> getBooking() {
        Booking booking = new Booking("0900 1700","Иванов И.И",
                new LocalDateTime(2017,1,12,0,0,0,0),2L);
        return new ResponseEntity<>(convert(booking), HttpStatus.OK);
    }

    private BookingDTO convert(Booking model) { return (model == null) ? null : new BookingDTO(model); }
}
