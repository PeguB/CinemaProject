package models;

import org.fis.cinemaregistrationapplication.models.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class MovieTests {

    @Test
    public void constructorTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        assert movie.getName().equals("Superman") && movie.getDuration_min().equals(10) && movie.getStart_date().equals("10:00");
    }

    @Test
    public void equalsTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Movie movie2 = new Movie("Superman", 10, "10:00");
        Movie movie3 = new Movie("Batman", 11, "10:00");
        Assertions.assertEquals(movie, movie2);
        Assertions.assertNotEquals(movie, movie3);
    }

    @Test
    public void hashCodeTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(movie.hashCode(), Objects.hash(movie.getId(), movie.getName()));
    }

    @Test
    public void getIdTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        movie.setId(3);
        int value = 3;
        Assertions.assertEquals(3, movie.getId());
    }

    @Test
    public void setIdTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(null, movie.getId());
        movie.setId(3);
        Assertions.assertEquals(3, movie.getId());
    }

    @Test
    public void getNameTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        Assertions.assertEquals("Superman", movie.getName());
    }

    @Test
    public void setNameTest() {
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertNotNull(movie);
        movie.setName("Batman");
        Assertions.assertEquals("Batman", movie.getName());
    }

    @Test
    public void getDuration_minTest(){
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertEquals(10,movie.getDuration_min());
    }

    @Test
    public void setDuration_minTest(){
        Movie movie = new Movie("Superman", 10, "10:00");
        movie.setDuration_min(3);
        Assertions.assertEquals(3,movie.getDuration_min());
    }

    @Test
    public void getStart_dateTest(){
        Movie movie = new Movie("Superman", 10, "10:00");
        Assertions.assertEquals("10:00", movie.getStart_date());
    }

    @Test
    public void setStart_dateTest(){
        Movie movie = new Movie("Superman", 10, "10:00");
        movie.setStart_date("11:00");
        Assertions.assertEquals("11:00",movie.getStart_date());
    }

}
