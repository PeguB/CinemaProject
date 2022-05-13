package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

public class HomePageClientController {

    @FXML
    protected void clickOnAdd(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    protected void clickOnInfo(){
        SceneSwitcher.switchScene("OneReservation.fxml");
    }
}
