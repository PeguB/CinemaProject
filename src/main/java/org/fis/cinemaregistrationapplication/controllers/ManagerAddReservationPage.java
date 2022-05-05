package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import org.fis.cinemaregistrationapplication.services.MoviesService;

import java.util.ArrayList;
import java.util.List;

public class ManagerAddReservationPage {

    private MoviesService moviesService = new MoviesService();
    @FXML
    private ComboBox movieName;

    @FXML
    private ComboBox date;

    @FXML
    private ChoiceBox seats;

    @FXML
    public void initialize() {
            movieName.getItems().addAll(moviesService.getMoviesName());
            String predefineMovieNameValue = moviesService.getMoviesName().stream().iterator().next();
            movieName.setValue(predefineMovieNameValue);
            date.setValue(moviesService.getStartDateByMovieName(movieName.getValue().toString()).get(0));
            date.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
            System.out.println(moviesService.getMoviesName());

    }

    @FXML
    public void setHoursBaseByMovieName(){
        if(movieName.getValue().toString() != null){
            date.getItems().clear();
            date.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
        }
        movieName.setOnAction(event->{
            date.getItems().clear();
            date.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));

        });
    }
}