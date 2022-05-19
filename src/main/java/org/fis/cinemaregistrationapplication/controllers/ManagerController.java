package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;
import org.fis.cinemaregistrationapplication.services.SingletonUsername;

public class ManagerController {

    @FXML
    public void clickOnAdd() {
        SceneSwitcher.switchScene("ManagerAddReservationPage.fxml");
    }

    @FXML
    public void clickOnConfirm() {
        SceneSwitcher.switchScene("ManagerConfirmPage.fxml");
    }

    @FXML
    public void clickOnEdit() {
        SceneSwitcher.switchScene("editReservation.fxml");
    }

    @FXML
    public void clickOnHistory() {
        SceneSwitcher.switchScene("HistoryPageManager.fxml");
    }

    @FXML
    public void clickOnDelete() {
        SceneSwitcher.switchScene("ManagerDelete.fxml");
    }

    @FXML
    public void goMainPage() {
        SingletonUsername.setUSSERNAME("");
        SceneSwitcher.switchScene("loginscreen.fxml");
    }
}
