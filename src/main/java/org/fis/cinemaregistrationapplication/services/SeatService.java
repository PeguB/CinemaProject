package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.Seat;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatService {

    public void addSeatsOnDatabse(String seats,String start_date){
        List<String> listSeatsNumber = Arrays.stream(seats.split(" ")).toList();

            listSeatsNumber.forEach(seat->{
                try {
                    String query = "INSERT INTO seat(number, reserved, start_date) VALUES(?, ?, ?)";
                    PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
                    statement.setString(1, seat);
                    statement.setString(2, "yes");
                    statement.setString(3, start_date);
                    statement.executeUpdate();
                } catch (java.sql.SQLIntegrityConstraintViolationException e){

                }catch (Exception e) {
                    System.out.println(e);
                }
            });
    }
}
