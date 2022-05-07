package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.DaysGenerator;
import org.fis.cinemaregistrationapplication.services.ReservationService;

public class ManagerConfirmedController {

    private ReservationService reservationService = new ReservationService();
    @FXML
    private ComboBox id;


    @FXML
    public void initialize(){
        id.getItems().addAll(reservationService.getReservationsWhitStatusUnknow());
        System.out.println(reservationService.getReservationsWhitStatusUnknow());
        id.setValue(reservationService.getReservationsWhitStatusUnknow().get(0));
    }
}
