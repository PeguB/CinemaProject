package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

public class HomePageClientController {

    @FXML
    public void clickOnAdd(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }
}
