package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.fis.cinemaregistrationapplication.Main;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;
import org.fis.cinemaregistrationapplication.services.SingletonUsername;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientReservationInfoController {

    @FXML
    protected void clickOnAdd(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    protected void clickOnInfo(){
        SceneSwitcher.switchScene("ClientReservationInfo.fxml");
    }

    @FXML
    protected void onHomeAction(){
        SceneSwitcher.switchScene("homePageClient.fxml");
    }

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void initialize() {

        try{
            ResultSet reservations = ReservationService.getAllReservations(SingletonUsername.getUSSERNAME());
            int row = 1;
            while(reservations.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneReservation.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Reservation Rez = new Reservation();
                Rez.setUsername_user(reservations.getString(2));
                Rez.setDate(reservations.getString(3));
                Rez.setMovieName(reservations.getString(4));
                Rez.setConfirmed(reservations.getString(5));
                Rez.setDay(reservations.getString(6));
                Rez.setSeat(reservations.getInt(7));
                OneReservationController oneReservationController = fxmlLoader.getController();
                oneReservationController.initialize(Rez, row);
                grid.add(anchorPane, 0, row++); //(child,column,row)

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }
}
