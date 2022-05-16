package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.models.Movie;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OnMovieController {
    @FXML
    private Text movie;

    public void initialize(Movie film) {
        movie.setText(film.getName());
    }

    public void onButtonAction(){
        try {
            MoviesService.setActual(MoviesService.getMovieByName(movie.getText()));
            SceneSwitcher.switchScene("MovieInformation.fxml");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
