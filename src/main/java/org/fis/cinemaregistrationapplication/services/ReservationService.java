package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.Reservation;

import java.sql.PreparedStatement;

public class ReservationService {

    public void addReservationToDatabase(Reservation reservation){
        try {
            String query = "INSERT INTO reservation(username_user, date, movie_name, comfirmed) VALUES(?, ?, ?,?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, reservation.getUsername_user());
            statement.setString(2, reservation.getDate());
            statement.setString(3, reservation.getMovieName());
            statement.setString(4, reservation.getConfirmed());

            statement.executeUpdate();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
