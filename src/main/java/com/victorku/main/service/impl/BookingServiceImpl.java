package com.victorku.main.service.impl;

import com.sun.xml.internal.bind.v2.TODO;
import com.victorku.main.model.Booking;
import com.victorku.main.repository.BookingRepository;
import com.victorku.main.service.BookingService;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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
    public Booking createBooking(String companyWorkTime, LocalDateTime requestDate, String employee, LocalDateTime bookingDate, Long bookingTime) {

        Booking booking = new Booking(companyWorkTime, requestDate, employee, bookingDate, bookingTime);
        LocalDateTime start = new LocalDateTime(bookingDate.getYear(), bookingDate.getMonthOfYear(), bookingDate.getDayOfMonth(), 0, 0, 0);
        LocalDateTime end = new LocalDateTime(bookingDate.getYear(), bookingDate.getMonthOfYear(), bookingDate.getDayOfMonth(), 23, 59, 59);

        List<Booking> bookingList = bookingRepository.findByBookingDateBetween(start, end);
        int[] workTimeArr = parseWorkTime(companyWorkTime);

        if (checkWorKTimeExtremums(bookingDate, bookingTime, workTimeArr) && checkTimeFree(booking, bookingList)) {
            return bookingRepository.save(booking);
        } else {
            return null;
        }
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDateTime start, LocalDateTime end) {
        List<Booking> bookingList = bookingRepository.findByBookingDateBetween(start, end);
        return bookingList;
    }

    // парсим строку с временем работы компании
    public int[] parseWorkTime(String str) {
        String[] parseArr = str.split(" ");
        int startHours = Integer.parseInt(parseArr[0].substring(0, 2));
        int startMinutes = Integer.parseInt(parseArr[0].substring(2, 4));
        int endHours = Integer.parseInt(parseArr[1].substring(0, 2));
        int endMinutes = Integer.parseInt(parseArr[1].substring(2, 4));
        int[] result = new int[]{startHours, startMinutes, endHours, endMinutes};
        return result;
    }

    // проверяем что время бронирования не выходит за пределы рабочих часов компании
    public boolean checkWorKTimeExtremums(LocalDateTime bookingDate, Long bookingTime, int[] workTimeArr) {
        int startHours = workTimeArr[0];
        int startMinutes = workTimeArr[1];
        int endHours = workTimeArr[2];
        int endMinutes = workTimeArr[3];
        boolean result = false;
        if (bookingDate.getHourOfDay() >= startHours && bookingDate.getMinuteOfHour() >= startMinutes &&
                (bookingDate.getHourOfDay() + bookingTime) <= endHours && bookingDate.getMinuteOfHour() <= endMinutes) {
            result = true;
        }
        return result;
    }

    // проверяем что бронь не накладывается на другие брони
    public boolean checkTimeFree(Booking currentBooking, List<Booking> existsBookings) {
        boolean result = false;
        if (existsBookings.size() != 0) {
            for (Booking b : existsBookings) {
                if ((currentBooking.getBookingDate().getHourOfDay() + currentBooking.getBookingTime()) <= b.getBookingDate().getHourOfDay() ||
                        currentBooking.getBookingDate().getHourOfDay() >= b.getBookingDate().getHourOfDay() + b.getBookingTime()) {
                    result = true;
                } else {
                    return false;
                }
            }
        } else {
            result = true;
        }
        return result;
    }
}
