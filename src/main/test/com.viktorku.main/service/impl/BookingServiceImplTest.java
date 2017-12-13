package com.viktorku.main.service.impl;

import com.victorku.main.model.Booking;
import com.victorku.main.service.impl.BookingServiceImpl;
import org.joda.time.LocalDateTime;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class BookingServiceImplTest {

    private String companyWorkTime = "0900 1700";
    private int[] workTimeArr = new int[]{9, 0, 17, 0};

    @Test
    public void parseWorkTime() throws Exception {
        assertArrayEquals(workTimeArr, new BookingServiceImpl().parseWorkTime(companyWorkTime));
    }

    @Test
    public void checkWorKTimeExtremums() throws Exception {
        assertTrue(new BookingServiceImpl().checkWorKTimeExtremums(new LocalDateTime(2011, 5, 12, 9, 0, 0), 2L, workTimeArr));
        assertTrue(new BookingServiceImpl().checkWorKTimeExtremums(new LocalDateTime(2011, 5, 12, 15, 0, 0), 2L, workTimeArr));
        assertFalse(new BookingServiceImpl().checkWorKTimeExtremums(new LocalDateTime(2011, 5, 12, 15, 1, 0), 2L, workTimeArr));
        assertFalse(new BookingServiceImpl().checkWorKTimeExtremums(new LocalDateTime(2011, 5, 12, 16, 0, 0), 2L, workTimeArr));
    }

    @Test
    public void checkTimeFree() throws Exception {
        assertTrue(new BookingServiceImpl().checkTimeFree(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 12, 12, 34, 54),
                "EMP 001", new LocalDateTime(2011, 05, 13, 12, 00, 00), 2L), new ArrayList<Booking>()));
        assertTrue(new BookingServiceImpl().checkTimeFree(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 12, 12, 34, 54),
                "EMP 001", new LocalDateTime(2011, 05, 13, 12, 00, 00), 2L), new ArrayList<Booking>() {{
            add(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 10, 12, 34, 54), "EMP 012",
                    new LocalDateTime(2011, 05, 13, 11, 00, 00), 1L));
        }}));
        assertTrue(new BookingServiceImpl().checkTimeFree(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 12, 12, 34, 54),
                "EMP 001", new LocalDateTime(2011, 05, 13, 12, 00, 00), 2L), new ArrayList<Booking>() {{
            add(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 11, 12, 35, 54), "EMP 014",
                    new LocalDateTime(2011, 05, 13, 14, 00, 00), 3L));
        }}));
        assertFalse(new BookingServiceImpl().checkTimeFree(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 12, 12, 34, 54),
                "EMP 001", new LocalDateTime(2011, 05, 13, 12, 00, 00), 2L), new ArrayList<Booking>() {{
            add(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 9, 12, 35, 54), "EMP 015",
                    new LocalDateTime(2011, 05, 13, 11, 00, 00), 2L));
        }}));
        assertFalse(new BookingServiceImpl().checkTimeFree(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 12, 12, 34, 54),
                "EMP 001", new LocalDateTime(2011, 05, 13, 12, 00, 00), 2L), new ArrayList<Booking>() {{
            add(new Booking(companyWorkTime, new LocalDateTime(2011, 05, 9, 12, 35, 54), "EMP 015",
                    new LocalDateTime(2011, 05, 13, 13, 00, 00), 2L));
        }}));
    }
}