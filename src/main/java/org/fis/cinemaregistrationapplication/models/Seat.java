package org.fis.cinemaregistrationapplication.models;

import java.util.Objects;

public class Seat {
    private Integer number;
    private String reserved;
    private String start_date;

    public Seat(Integer number, String reserved,String start_date) {
        this.number = number;
        this.reserved = reserved;
        this.start_date = start_date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(number, seat.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

}
