package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

import java.util.List;

public class ManagerDeleteController {

    private ReservationService reservationService = new ReservationService();
    @FXML
    private ComboBox id;

    @FXML
    private Button deleteButton;

    @FXML
    private Label message;

    @FXML
    public void initialize(){
        List<String> listReservationUnknown = reservationService.getIdReservations();
        if(listReservationUnknown.isEmpty()){
            message.setText("No reservations to delete");
        }else{
            id.getItems().addAll(listReservationUnknown);
            System.out.println(reservationService.getIdReservations());
            id.setValue(reservationService.getIdReservations());
            message.setText("");
        }
    }

    @FXML
    public void goHome(){
        SceneSwitcher.switchScene("HomePageManager.fxml");
    }

    @FXML
    public void clickOnDelete(){
        try {
            Integer Id = Integer.parseInt(id.getValue().toString());
            reservationService.deleteReservation(Id);
            message.setText("Update the status of reservation whit id: " + id.getValue().toString());
        } catch (RuntimeException e) {
            message.setText("Invalid id");
        } finally {
            id.getItems().clear();
            id.getItems().addAll(reservationService.getIdReservations());
        }
    }
}
