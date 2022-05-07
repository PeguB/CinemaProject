package org.fis.cinemaregistrationapplication.models;


import java.util.Objects;

public class Reservation {
    private Integer id;
    private String username_user;
    private String movieName;
    private String date;
    private String confirmed;

    public Reservation(){}
    public Reservation(String username_user, String movieName, String date) {
        this.username_user = username_user;
        this.movieName = movieName;
        this.date = date;
    }

    public Reservation(String username_user, String movieName, String date, String comfirmed) {
        this.username_user = username_user;
        this.movieName = movieName;
        this.date = date;
        this.confirmed = comfirmed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getUsername_user() {
        return username_user;
    }

    public void setUsername_user(String username_user) {
        this.username_user = username_user;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", username_user='" + username_user + '\'' +
                ", movieName='" + movieName + '\'' +
                ", date='" + date + '\'' +
                ", confirmed='" + confirmed + '\'' +
                '}';
    }
}
