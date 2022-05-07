package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            String query = "SELECT * FROM reservation WHERE movie_name = ? AND date = ? AND seat_reserved = ? AND day = ?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1,movie_name);
            statement.setString(2,hour);
            statement.setString(3,seat.toString());
            statement.setString(4,day);
            ResultSet result = statement.executeQuery();
          return result.next();

    }
}
