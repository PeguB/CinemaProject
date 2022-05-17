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

    @FXML
    private Text id_rezer;

    public void initialize(Reservation Rez, Integer row) {

        try{
            day.setText(Rez.getDay());
            hour.setText(Rez.getDate());
            seat.setText(Rez.getSeat().toString());
            status.setText(Rez.getConfirmed());
            movie.setText(Rez.getMovieName());
            id.setText(row.toString());
            String ID = Rez.getId().toString();
            id_rezer.setText(ID);
        }catch(NullPointerException e){

        }

    }

}
