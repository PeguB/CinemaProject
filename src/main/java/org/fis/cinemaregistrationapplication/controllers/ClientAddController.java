package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.fis.cinemaregistrationapplication.services.DaysGenerator;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.ReservationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientAddController {

    private MoviesService moviesService = new MoviesService();
    private ReservationService reservationService = new ReservationService();

    @FXML
    private ComboBox seat;

    @FXML
    private ComboBox movie;

    @FXML
    private ComboBox day;

    @FXML
    private ComboBox hour;

    @FXML
    public void initialize(){

        ArrayList<Integer> Seats = new ArrayList<Integer>();
        for(int i = 1; i < 49; i++)
            Seats.add(i);

        seat.getItems().addAll(Seats);
        day.getItems().addAll(DaysGenerator.getDays());

        Set<String> Movies = moviesService.getMoviesName();
        if(!Movies.isEmpty()){
            movie.getItems().addAll(Movies);
            movie.setValue(Movies.iterator().next());
            hour.getItems().addAll(moviesService.getStartDateByMovieName(movie.getValue().toString()));
        }

    }

    protected void onCheckAction(){

    }
}
