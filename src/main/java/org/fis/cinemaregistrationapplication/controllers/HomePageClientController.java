package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.fis.cinemaregistrationapplication.Main;
import org.fis.cinemaregistrationapplication.models.Movie;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;
import org.fis.cinemaregistrationapplication.services.SingletonUsername;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePageClientController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;


    @FXML
    public void initialize() {

        try{
            ResultSet movies = MoviesService.getAllMovies();
            int row = 1;
            while(movies.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("OneMovie.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Movie film = new Movie(movies.getString(2), movies.getInt(3), movies.getString(4));
                OnMovieController oneMovieController = fxmlLoader.getController();
                oneMovieController.initialize(film);
                grid.add(anchorPane, 0, row++); //(child,column,row)

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }


    @FXML
    protected void clickOnAdd(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    protected void clickOnInfo(){
        SceneSwitcher.switchScene("ClientReservationInfo.fxml");
    }

    @FXML
    protected void onLogOutAction(){
        SceneSwitcher.switchScene("loginscreen.fxml");
    }

    @FXML
    protected void onHomeAction(){
        SceneSwitcher.switchScene("homePageClient.fxml");
    }
}
