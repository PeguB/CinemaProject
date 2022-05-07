package org.fis.cinemaregistrationapplication.models;

import java.util.Objects;

public class Movie {
    private Integer id;
    private String name;
    private Integer duration_min;
    private String start_date;

    public Movie(String name, Integer duration, String start_date) {
        this.name = name;
        this.duration_min = duration;
        this.start_date = start_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(Integer duration_min) {
        this.duration_min = duration_min;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
