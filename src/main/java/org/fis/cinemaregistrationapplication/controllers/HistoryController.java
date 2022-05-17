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

public class HistoryController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void goHome(){
        SceneSwitcher.switchScene("HomePageManager.fxml");
    }
    @FXML
    public void initialize() {

        try{
            ResultSet reservations = ReservationService.getAllReservations();
            int row = 1;
            while(reservations.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneReservationManager.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Reservation Rez = new Reservation();
                Rez.setId(Integer.valueOf(reservations.getString(1)));
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
