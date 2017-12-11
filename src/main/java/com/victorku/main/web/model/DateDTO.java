package com.victorku.main.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDateTime;

public class DateDTO {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;

    public DateDTO() {
    }

    public DateDTO(LocalDateTime date) {
        if (date == null) {
            return;
        }
        year = date.getYear();
        month = date.getMonthOfYear();
        day = date.getDayOfMonth();
        hours = date.getHourOfDay();
        minutes = date.getMinuteOfHour();
        seconds = date.getSecondOfMinute();
    }

    @JsonIgnore
    public LocalDateTime getLocalDateData()
    {
        try {
            return new LocalDateTime(year, month, day,hours,minutes,seconds);
        }catch (IllegalArgumentException illegalArgument) {
            return new LocalDateTime(1970,1,1,0,0,0);
        }
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
}
