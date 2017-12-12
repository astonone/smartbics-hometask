package com.victorku.main.web.model;

import com.victorku.main.model.Booking;

import java.util.ArrayList;
import java.util.List;

public class RequestBookingEntityDTO {

    private String companyWorkTime;
    private List<BookingDTO> bookingsList;

    public RequestBookingEntityDTO() {
    }

    public RequestBookingEntityDTO(String companyWorkTime, List<Booking> dbModel) {

        if (dbModel == null) {
            return;
        }

        this.companyWorkTime = companyWorkTime;

        List<BookingDTO> bookingList = new ArrayList<>();
        for (Booking booking : dbModel) {
            bookingList.add(new BookingDTO(booking));
        }
        this.bookingsList = bookingList;
    }

    public String getCompanyWorkTime() {
        return companyWorkTime;
    }

    public void setCompanyWorkTime(String companyWorkTime) {
        this.companyWorkTime = companyWorkTime;
    }

    public List<BookingDTO> getBookingsList() {
        return bookingsList;
    }

    public void setBookingsList(List<BookingDTO> bookingsList) {
        this.bookingsList = bookingsList;
    }
}
