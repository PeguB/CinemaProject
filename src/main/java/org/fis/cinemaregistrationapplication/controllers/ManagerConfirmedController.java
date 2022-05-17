package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.DaysGenerator;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

import java.util.List;
import java.util.Optional;

public class ManagerConfirmedController {

    private ReservationService reservationService = new ReservationService();
    @FXML
    private ComboBox id;

    @FXML
    private ComboBox status;

    @FXML
    private Label text;

    @FXML
    public void initialize() {
        List<String> listReservationUnknown = reservationService.getReservationsWhitStatusUnknow();
        if(listReservationUnknown.isEmpty()){
            text.setText("No reservations to check");
        }else{
            id.getItems().addAll(listReservationUnknown);
            System.out.println(reservationService.getReservationsWhitStatusUnknow());
            id.setValue(reservationService.getReservationsWhitStatusUnknow().get(0));
            status.getItems().addAll("Confirmed", "Reject");
            text.setText("");
        }

    }

    @FXML
    public void goHome(){
        SceneSwitcher.switchScene("HomePageManager.fxml");
    }

    @FXML
    public void sendSubmitConfirm() {
        try {
            Integer Id = Integer.parseInt(id.getValue().toString());
            reservationService.updateReservationStatus(Id, status.getValue().toString());
            text.setText("Update the status of reservation whit id: " + id.getValue().toString() +" "+status.getValue().toString());
        } catch (RuntimeException e) {
            text.setText("Invalid id");
        } finally {
            id.getItems().clear();
            id.getItems().addAll(reservationService.getReservationsWhitStatusUnknow());
        }


    }

}


