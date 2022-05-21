package services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservartionTests {


   private ReservationService reservationService = new ReservationService();


    @BeforeAll
    public static void initialize(){
        DBConnection.CreateConnection();
    }

    @AfterAll
    public static void close(){
        DBConnection.closeConnection();
    }

    @Test
    public void deleteReservation(){

        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Confirmed");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);
        reservationService.addReservationToDatabase(reservation);
         Integer nr =  reservationService.getIdReservations().size();
         String id =  reservationService.getIdReservations().get(nr - 1);
        reservationService.deleteReservation(Integer.valueOf(id));
       Optional<String> findId =  reservationService.getIdReservations().stream()
                .filter(data -> data.equals(id))
                .findFirst();
        Assertions.assertEquals(true,findId.isEmpty());
    }

    @Test
    public void getIdReservationsTest(){

        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Confirmed");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        Reservation reservation2 = new Reservation();
        reservation2.setUsername_user("xxx");
        reservation2.setMovieName("Batman");
        reservation2.setConfirmed("Confirmed");
        reservation2.setDate("11:00");
        reservation2.setDay("Friday");
        reservation2.setSeat(300);
        Integer size = reservationService.getIdReservations().size();
        reservationService.addReservationToDatabase(reservation);
        reservationService.addReservationToDatabase(reservation2);
        Assertions.assertEquals(size + 2, reservationService.getIdReservations().size());
        Integer newSize = reservationService.getIdReservations().size();
        reservationService.deleteReservation(Integer.parseInt(reservationService.getIdReservations().get(newSize -1)));
        reservationService.deleteReservation(Integer.parseInt(reservationService.getIdReservations().get(newSize -2)));

    }

    @Test
    public void addReservationToDatabaseTest(){

        Integer size = reservationService.getIdReservations().size();

        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Confirmed");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        Integer newSize = reservationService.getIdReservations().size();

        Assertions.assertEquals(size + 1, newSize);
        reservationService.deleteReservation(Integer.parseInt(reservationService.getIdReservations().get(newSize -1)));
        Assertions.assertEquals(size, reservationService.getIdReservations().size());

    }

    @Test
    public void  getReservationsWhitStatusUnknowTest(){
        Integer size = reservationService.getReservationsWhitStatusUnknow().size();

        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        Integer nr = reservationService.getIdReservations().size();
        Integer newSize = reservationService.getReservationsWhitStatusUnknow().size();

        Assertions.assertEquals(size + 1, newSize);
        reservationService.deleteReservation(Integer.parseInt(reservationService.getIdReservations().get(nr -1)));
        Assertions.assertEquals(size, reservationService.getReservationsWhitStatusUnknow().size());
    }

    @Test
    public void getReservationsIdWhitStatusComfirmedTest(){
        Integer size = reservationService.getIdReservations().size();
        Integer sizeStatus = reservationService.getReservationsIdWhitStatusComfirmed().size();
        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Confirmed");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        Integer nr = reservationService.getIdReservations().size();
        Integer newSize = reservationService.getReservationsIdWhitStatusComfirmed().size();

        Assertions.assertEquals(sizeStatus + 1, newSize);
        Integer Id = Integer.parseInt(reservationService.getIdReservations().get(nr -1));
        reservationService.deleteReservation(Id);
        Assertions.assertEquals(sizeStatus, reservationService.getReservationsIdWhitStatusComfirmed().size());
    }
    @Test
    public void updateReservation() throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        int newSize = reservationService.getIdReservations().size();
        Integer Id_database = Integer.parseInt(reservationService.getIdReservations().get(newSize -1));
        reservationService.updateReservation(Id_database,reservation.getDate(),"Superman","Monday",500);
        ResultSet allReservations = ReservationService.getAllReservations();
        while (allReservations.next()){
             String id = allReservations.getString("id");
             String movie_name = allReservations.getString("movie_name");
             String day = allReservations.getString("day");
             String seat = allReservations.getString("seat_reserved");
             if(id.equals(Id_database.toString()) && movie_name.equals("Superman"
             ) && day.equals("Monday") && seat.equals("500")  ){
                 assert true;
             }
        }
        reservationService.deleteReservation(Id_database);

    }
    @Test
    public void getAllReservationsByUsernameTest() throws SQLException {

        ResultSet resultSetInitial = ReservationService.getAllReservations("xxxxxxxx");
        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxxxxxxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);


        reservationService.addReservationToDatabase(reservation);
        Integer nr = reservationService.getIdReservations().size();
        ResultSet resultSetTested = ReservationService.getAllReservations("xxxxxxxx");
        int inital = 0;
        int tested = 0;
        while(resultSetInitial.next()){
            inital++;
        }

        while(resultSetTested.next()){
            tested++;
        }

        Assertions.assertEquals(tested,inital+1);

        int newSize = reservationService.getIdReservations().size();
        Integer idTestedElement = Integer.parseInt(reservationService.getIdReservations().get(nr - 1));
        reservationService.deleteReservation(idTestedElement);
    }

    @Test
    public void seatOcupatedTest() throws SQLException {



        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxxxxxxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        Integer nr = reservationService.getIdReservations().size();
        Assertions.assertEquals(true,reservationService.seatOcupated(reservation.getMovieName(),reservation.getDate(),reservation.getSeat(),reservation.getDay()));

        Integer id_Tested = Integer.parseInt(reservationService.getIdReservations().get(nr - 1));
        reservationService.deleteReservation(id_Tested);
    }

    @Test
    public void availableSeatByNumberOfSeats() throws SQLException {

        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxxxxxxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        Boolean availability = reservationService.availableSeatByNumberOfSeats(reservation.getMovieName(),reservation.getDate(),reservation.getDay());
       Assertions.assertTrue(availability);

    }

    @Test
    public void updateReservationStatus(){


        Reservation reservation = new Reservation();
        reservation.setUsername_user("xxxxxxxx");
        reservation.setMovieName("Batman");
        reservation.setConfirmed("Unknown");
        reservation.setDate("11:00");
        reservation.setDay("Friday");
        reservation.setSeat(300);

        reservationService.addReservationToDatabase(reservation);
        Integer size = reservationService.getIdReservations().size();
        Integer id = Integer.parseInt(reservationService.getIdReservations().get(size-1));
        reservationService.updateReservationStatus(id,"Confirmed");
        reservationService.deleteReservation(id);
        Assertions.assertEquals(size - 1,reservationService.getIdReservations().size());
    }
}
