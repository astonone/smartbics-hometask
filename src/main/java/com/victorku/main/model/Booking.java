package com.victorku.main.model;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "worktime")
    private String companyWorkTime;

    @Column(name = "date", insertable = false, updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @DateTimeFormat(pattern = "yyyy/dd/mm H:m:s:S")
    private LocalDateTime requestDate;

    @Column(name = "employee")
    private String employee;

    @Column(name = "bookingdate")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @DateTimeFormat(pattern = "yyyy/dd/mm H:m:s:S")
    private LocalDateTime bookingDate;

    @Column(name = "bookingtime")
    private Long bookingTime;

    public Booking() {
        this.requestDate = new LocalDateTime();
    }

    public Booking(String companyWorkTime, String employee, LocalDateTime bookingDate, Long bookingTime) {
        this();
        this.companyWorkTime = companyWorkTime;
        this.employee = employee;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
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

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Long bookingTime) {
        this.bookingTime = bookingTime;
    }
}