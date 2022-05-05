package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ManagerAddReservationPage {

    @FXML
    private ChoiceBox movieName;

    @FXML
    private ChoiceBox date;

    @FXML
    private ChoiceBox seats;

    @FXML
    public void initialize() {
            movieName.getItems().addAll("Batman");
    }
}