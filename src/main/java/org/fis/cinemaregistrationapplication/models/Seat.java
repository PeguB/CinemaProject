package org.fis.cinemaregistrationapplication.models;

import java.util.Objects;

public class Seat {
    private Integer number;
    private String reserved;

    public Seat(Integer number, String reserved) {
        this.number = number;
        this.reserved = reserved;
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
}
