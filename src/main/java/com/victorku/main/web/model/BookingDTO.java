package com.victorku.main.web.model;

import com.victorku.main.model.Booking;
import org.joda.time.LocalDateTime;

public class BookingDTO {

    private long id;
    private String companyWorkTime;
    private DateDTO requestDate;
    private String employee;
    private DateDTO bookingDate;
    private Long bookingTime;

    public BookingDTO() {
    }

    public BookingDTO(Booking model) {
        if (model == null) {
            return;
        }

        this.id = model.getId();
        this.companyWorkTime = model.getCompanyWorkTime();
        this.requestDate = new DateDTO(model.getRequestDate());
        this.employee = model.getEmployee();
        this.bookingDate = new DateDTO(model.getBookingDate());
        this.bookingTime = model.getBookingTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyWorkTime() {
        return companyWorkTime;
    }

    public void setCompanyWorkTime(String companyWorkTime) {
        this.companyWorkTime = companyWorkTime;
    }

    public DateDTO getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(DateDTO requestDate) {
        this.requestDate = requestDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public DateDTO getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(DateDTO bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Long bookingTime) {
        this.bookingTime = bookingTime;
    }
}
