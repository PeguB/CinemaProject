package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

public class ManagerController {

    @FXML
    public void clickOnAdd(){
        SceneSwitcher.switchScene("ManagerAddReservationPage.fxml");
    }

    @FXML
    public void clickOnConfirm(){
        SceneSwitcher.switchScene("ManagerConfirmPage.fxml");
    }

    @FXML
    public void clickOnEdit(){SceneSwitcher.switchScene("editReservation.fxml");}
}
