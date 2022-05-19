package models;

import org.fis.cinemaregistrationapplication.models.Movie;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ReservationTest {

    @Test
    public void noArgsConstructor() {
        Reservation reservation = new Reservation();
        Assertions.assertNotNull(reservation);
        Assertions.assertNotNull(reservation);
        Assertions.assertNull(reservation.getDay());
        Assertions.assertNull(reservation.getSeat());
        Assertions.assertNull(reservation.getConfirmed());
        Assertions.assertNull(reservation.getId());
        Assertions.assertNull(reservation.getDate());
        Assertions.assertNull(reservation.getMovieName());
        Assertions.assertNull(reservation.getUsername_user());

    }

    @Test
    public void threeArgsConstructor() {
        Reservation reservation = new Reservation("John", "Batman", "11:00");
        Assertions.assertNotNull(reservation);
        Assertions.assertNull(reservation.getDay());
        Assertions.assertNull(reservation.getSeat());
        Assertions.assertNull(reservation.getConfirmed());
        Assertions.assertNull(reservation.getId());
        Assertions.assertEquals("11:00", reservation.getDate());
        Assertions.assertEquals("Batman", reservation.getMovieName());
        Assertions.assertEquals("John", reservation.getUsername_user());
    }

    @Test
    public void fourArgsConstructor() {
        Reservation reservation = new Reservation("John", "Batman", "11:00", "Confirmed");
        Assertions.assertNotNull(reservation);
        Assertions.assertNull(reservation.getDay());
        Assertions.assertNull(reservation.getSeat());
        Assertions.assertEquals("Confirmed", reservation.getConfirmed());
        Assertions.assertNull(reservation.getId());
        Assertions.assertEquals("11:00", reservation.getDate());
        Assertions.assertEquals("Batman", reservation.getMovieName());
        Assertions.assertEquals("John", reservation.getUsername_user());
    }

    @Test
    public void getDayTest() {
        Reservation reservation = new Reservation();
        reservation.setDay("Monday");
        Assertions.assertEquals("Monday", reservation.getDay());
    }

    @Test
    public void setDayTest() {
        Reservation reservation = new Reservation();
        reservation.setDay("Monday");
        reservation.setDay("Friday");
        Assertions.assertEquals("Friday", reservation.getDay());
    }

    @Test
    public void getSeatTest() {
        Reservation reservation = new Reservation();
        reservation.setSeat(3);

        Assertions.assertEquals(3, reservation.getSeat());
    }

    @Test
    public void setSeatTest() {
        Reservation reservation = new Reservation();
        reservation.setSeat(3);
        reservation.setSeat(4);
        Assertions.assertEquals(4, reservation.getSeat());
    }

    @Test
    public void getIdTest() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        Assertions.assertEquals(1, reservation.getId());

    }

    @Test
    public void setIdTest() {
        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setId(2);
        Assertions.assertEquals(2, reservation.getId());

    }

    @Test
    public void equalsTest() {
        Reservation reservation = new Reservation();
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        reservation.setId(1);
        reservation1.setId(1);
        reservation2.setId(3);
        Assertions.assertEquals(reservation, reservation1);
        Assertions.assertNotEquals(reservation, reservation2);
    }

    @Test
    public void hashCodeTest() {
        Reservation reservation = new Reservation();
        Assertions.assertNotNull(reservation);
        Assertions.assertEquals(reservation.hashCode(), Objects.hash(reservation.getId()));
    }

    @Test
    public void getUsername_userTest() {
        Reservation reservation = new Reservation();
        reservation.setUsername_user("Sergiu");
        Assertions.assertEquals("Sergiu", reservation.getUsername_user());
    }

    @Test
    public void setUsername_userTest() {
        Reservation reservation = new Reservation();
        reservation.setUsername_user("Sergiu");
        reservation.setUsername_user("Sergiu2");
        Assertions.assertEquals("Sergiu2", reservation.getUsername_user());
    }

    @Test
    public void getMovieNameTest() {
        Reservation reservation = new Reservation();
        reservation.setMovieName("name");
        Assertions.assertEquals("name", reservation.getMovieName());
    }

    @Test
    public void setMovieNameTest() {
        Reservation reservation = new Reservation();
        reservation.setMovieName("name");
        reservation.setMovieName("name2");
        Assertions.assertEquals("name2", reservation.getMovieName());
    }

    @Test
    public void getDateTest() {
        Reservation reservation = new Reservation();
        Assertions.assertNull(reservation.getDate());
    }

    @Test
    public void setDateTest() {
        Reservation reservation = new Reservation();
        reservation.setDate("20:00");
        Assertions.assertEquals("20:00", reservation.getDate());
    }

    @Test
    public void getConfirmedTest() {
        Reservation reservation = new Reservation();
        Assertions.assertNull(reservation.getConfirmed());
    }

    @Test
    public void setConfirmedTest() {
        Reservation reservation = new Reservation();
        reservation.setConfirmed("Unknown");
        Assertions.assertEquals("Unknown", reservation.getConfirmed());
    }

    @Test
    public void toStringTest(){
        Reservation reservation = new Reservation("John", "Batman", "11:00", "Confirmed");
        String value = "Reservation{" +
                "id=" + null +
                ", username_user='" + "John" + '\'' +
                ", movieName='" + "Batman" + '\'' +
                ", date='" + "11:00" + '\'' +
                ", confirmed='" + "Confirmed" + '\'' +
                ", day='" + null + '\'' +
                ", seat=" + null +
                '}';
        Assertions.assertEquals(value,reservation.toString());
    }
}
