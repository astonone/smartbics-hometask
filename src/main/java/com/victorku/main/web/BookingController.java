package com.victorku.main.web;

import com.victorku.main.exceptions.ApplicationErrorTypes;
import com.victorku.main.model.Booking;
import com.victorku.main.service.BookingService;
import com.victorku.main.web.model.BookingDTO;
import com.victorku.main.web.model.ErrorResponseBody;
import com.victorku.main.web.model.ListBookingsDTO;
import com.victorku.main.web.model.RequestBookingEntityDTO;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBooking(@PathVariable("id") Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            return getErrorResponseBody(ApplicationErrorTypes.BOOKING_ID_NOT_FOUND);
        }
        return new ResponseEntity<>(convert(booking), HttpStatus.OK);
    }

    @RequestMapping(value = "/request", method = RequestMethod.PUT)
    public ResponseEntity<?> bookingRequest(@RequestBody RequestBookingEntityDTO requestBookingEntityDTO) {
        List<Booking> bookingList = new ArrayList<>();

        // Сортируем брони по времени подачи заявок
        Collections.sort(requestBookingEntityDTO.getBookingsList(),
                (t2, t1) -> (int) (t2.getRequestDate().getLocalDateData().toDateTime().getMillis() - t1.getRequestDate().getLocalDateData().toDateTime().getMillis()));

        for (BookingDTO bookingDTO : requestBookingEntityDTO.getBookingsList()) {
            LocalDateTime requestDate = bookingDTO.getRequestDate() == null ? null : bookingDTO.getRequestDate().getLocalDateData();
            LocalDateTime bookingDate = bookingDTO.getBookingDate() == null ? null : bookingDTO.getBookingDate().getLocalDateData();
            Booking booking = bookingService.bookingRequest(requestBookingEntityDTO.getCompanyWorkTime(), requestDate, bookingDTO.getEmployee(),
                    bookingDate, bookingDTO.getBookingTime());
            if (booking != null) {
                bookingList.add(booking);
            }
        }
        // Сортируем заявки для группировки до дням на выходе
        Collections.sort(bookingList,
                (t2, t1) -> (int) (t2.getBookingDate().toDateTime().getMillis() - t1.getBookingDate().toDateTime().getMillis()));

        return new ResponseEntity<>(convert(bookingList), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseEntity<?> create(@RequestBody ListBookingsDTO listBookingsDTO) {

        for (BookingDTO bookingDTO : listBookingsDTO.getBookingsDTOList()) {
            LocalDateTime requestDate = bookingDTO.getRequestDate() == null ? null : bookingDTO.getRequestDate().getLocalDateData();
            LocalDateTime bookingDate = bookingDTO.getBookingDate() == null ? null : bookingDTO.getBookingDate().getLocalDateData();
            Booking booking = bookingService.create(bookingDTO.getCompanyWorkTime(), requestDate, bookingDTO.getEmployee(),
                    bookingDate, bookingDTO.getBookingTime());
        }

        return new ResponseEntity<>(convert((Booking) null), HttpStatus.OK);
    }

    @RequestMapping(value = "/findByDate", method = RequestMethod.GET)
    public ResponseEntity<?> getBookingList(@RequestParam("year") Integer year, @RequestParam("month") Integer month,
                                            @RequestParam("day") Integer day) {
        LocalDateTime start = new LocalDateTime(year, month, day, 0, 0, 0);
        LocalDateTime end = new LocalDateTime(year, month, day, 23, 59, 59);
        List<Booking> bookingList = bookingService.getBookingsByDate(start, end);
        return new ResponseEntity<>(convert(bookingList), HttpStatus.OK);
    }


    private BookingDTO convert(Booking model) {
        return (model == null) ? null : new BookingDTO(model);
    }

    private ListBookingsDTO convert(List<Booking> dbModel) {
        return (dbModel == null) ? null : new ListBookingsDTO(dbModel);
    }

    private ResponseEntity<ErrorResponseBody> getErrorResponseBody(ApplicationErrorTypes errorType) {
        return new ResponseEntity<>(new ErrorResponseBody(errorType), HttpStatus.NOT_FOUND);
    }
}
