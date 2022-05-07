package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.*;

import java.util.ArrayList;
import java.util.List;

public class ManagerAddReservationPage {

    private MoviesService moviesService = new MoviesService();
    private ReservationService reservationService = new ReservationService();
    private SeatService seatService = new SeatService();

    private UserLoginService userLoginService = new UserLoginService();
    @FXML
    private ComboBox movieName;

    @FXML
    private ComboBox date;

    @FXML
    private TextField seats;

    @FXML
    private TextField message;

    @FXML
    private ComboBox username_user;

    @FXML
    public void initialize() {
            movieName.getItems().addAll(moviesService.getMoviesName());
            String predefineMovieNameValue = moviesService.getMoviesName().stream().iterator().next();
            movieName.setValue(predefineMovieNameValue);
            date.setValue(moviesService.getStartDateByMovieName(movieName.getValue().toString()).get(0));
            date.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
            username_user.getItems().addAll(userLoginService.getUsersFromDatabase());
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
    @FXML
    public void onSubmitButton(){
        if(date.getValue().toString() != null && movieName.getValue().toString() != null && seats.getText().isEmpty() == false){
            Reservation reservation = new Reservation();
            reservation.setUsername_user(username_user.getValue().toString());
            reservation.setDate(date.getValue().toString());
            reservation.setMovieName(movieName.getValue().toString());
            reservation.setConfirmed("Confirmed");
            System.out.println(reservation);
            seatService.addSeatsOnDatabse(seats.getText(),date.getValue().toString());

            reservationService.addReservationToDatabase(reservation);
            message.setText("Seat Register");
        }else{
            System.out.println(message.getText());
            message.setText("Must complete all fields");
        }

    }
}