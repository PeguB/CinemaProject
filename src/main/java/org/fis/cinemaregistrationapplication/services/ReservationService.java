package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationService {

    public void addReservationToDatabase(Reservation reservation) {
        try {
            String query = "INSERT INTO reservation(username_user, date, movie_name, comfirmed, day, seat_reserved) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, reservation.getUsername_user());
            statement.setString(2, reservation.getDate());
            statement.setString(3, reservation.getMovieName());
            statement.setString(4, reservation.getConfirmed());
            statement.setString(5, reservation.getDay());
            statement.setString(6, reservation.getSeat().toString());
            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean seatOcupated(String movie_name, String hour, Integer seat, String day) throws SQLException {
            String query = "SELECT * FROM reservation WHERE movie_name = ? AND date = ? AND seat_reserved = ? AND day = ? AND (comfirmed = 'Unknown' OR comfirmed = 'Confirmed') ";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1,movie_name);
            statement.setString(2,hour);
            statement.setString(3,seat.toString());
            statement.setString(4,day);
            ResultSet result = statement.executeQuery();
          return result.next();

    }

    public static ResultSet getAllReservations(String username) throws SQLException {
        String query = "SELECT * FROM reservation WHERE username_user = ?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        statement.setString(1, username);
        ResultSet user_reservations = statement.executeQuery();

        return user_reservations;
    }

    public static ResultSet getAllReservations() throws SQLException {
        String query = "SELECT * FROM reservation";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        ResultSet reservations = statement.executeQuery();

        return reservations;
    }

    public boolean availableSeatByNumberOfSeats(String name, String hour, String day) throws SQLException {
        String query = "SELECT Count(seat_reserved) as 'number_seats', movie_name, day, date FROM reservation WHERE movie_name = ? AND date = ? AND day = ? GROUP BY movie_name,day,date ";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        statement.setString(1,name);
        statement.setString(2,hour);
        statement.setString(3,day);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            String number_seats = result.getString("number_seats");
            if(Integer.parseInt(number_seats) == 50){
                return false;
            }
        }
        return true;

    }
    public List<String> getReservationsWhitStatusUnknow(){
        String query = "SELECT * FROM accounts.reservation where comfirmed = 'Unknown'";
        List<String> list_reservations = new ArrayList<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list_reservations.add(result.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_reservations;
    }
    public void updateReservationStatus(Integer Id, String staus){
        String query = "Update reservation Set comfirmed = ? WHERE id = ?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1,staus);
            statement.setString(2,Id.toString());
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<String> getReservationsIdWhitStatusComfirmed(){
        String query = "SELECT * FROM accounts.reservation where comfirmed = 'Confirmed'";
        List<String> list_reservations = new ArrayList<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list_reservations.add(result.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_reservations;
    }

    public void updateReservation(Integer Id, String hour, String movie_name, String day, Integer seat) {
        String query = "Update reservation Set date = ?, movie_name = ?, day = ?, seat_reserved = ? WHERE id = ?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1,hour);
            statement.setString(2,movie_name);
            statement.setString(3,day);
            statement.setString(4,seat.toString());
            statement.setString(5,Id.toString());
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


