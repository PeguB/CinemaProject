package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.models.Reservation;

public class OneReservationController {

    @FXML
    private Text day;

    @FXML
    private Text hour;

    @FXML
    private Text seat;

    @FXML
    private Text status;

    @FXML
    private Text movie;

    @FXML
    private Text id;

    public void initialize(Reservation Rez, Integer row) {

        day.setText(Rez.getDay());
        hour.setText(Rez.getDate());
        seat.setText(Rez.getSeat().toString());
        status.setText(Rez.getConfirmed());
        movie.setText(Rez.getMovieName());
        id.setText(row.toString());
    }

}
