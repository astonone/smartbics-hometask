package com.victorku.main.web.model;

import com.victorku.main.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class ListBookingsDTO {

    private List<BookingDTO> bookingsDTOList;

    public ListBookingsDTO() {
    }

    public ListBookingsDTO(List<Booking> dbModel) {

        if (dbModel == null) {
            return;
        }

        List<BookingDTO> bookingList = new ArrayList<>();
        for (Booking booking : dbModel) {
            bookingList.add(new BookingDTO(booking));
        }
        this.bookingsDTOList = bookingList;
    }

    public List<BookingDTO> getBookingsDTOList() {
        return bookingsDTOList;
    }

    public void setBookingsDTOList(List<BookingDTO> bookingsDTOList) {
        this.bookingsDTOList = bookingsDTOList;
    }
}
