package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.models.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnMovieController {
    @FXML
    private Text movie;

    public void initialize() {

    }
}
