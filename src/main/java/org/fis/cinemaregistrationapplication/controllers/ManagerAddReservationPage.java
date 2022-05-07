package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerAddReservationPage {

    private MoviesService moviesService = new MoviesService();
    private ReservationService reservationService = new ReservationService();

    private UserLoginService userLoginService = new UserLoginService();
    @FXML
    private ComboBox movieName;

    @FXML
    private ComboBox date;

    @FXML
    private TextField seat;

    @FXML
    private Label message;

    @FXML
    private ComboBox username_user;

    @FXML
    private ComboBox day;

    @FXML
    public void initialize() {
            movieName.getItems().addAll(moviesService.getMoviesName());
            String predefineMovieNameValue = moviesService.getMoviesName().stream().iterator().next();
            movieName.setValue(predefineMovieNameValue);
            date.setValue(moviesService.getStartDateByMovieName(movieName.getValue().toString()).get(0));
            date.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
            username_user.getItems().addAll(userLoginService.getUsersFromDatabase());
            day.getItems().addAll(DaysGenerator.getDays());
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
    public void onSubmitButton()  {
        if(date.getValue().toString() != null && movieName.getValue().toString() != null && seat.getText().isEmpty() == false){
            Reservation reservation = new Reservation();
            reservation.setUsername_user(username_user.getValue().toString());
            reservation.setDate(date.getValue().toString());
            reservation.setMovieName(movieName.getValue().toString());
            reservation.setConfirmed("Confirmed");
            reservation.setDay(day.getValue().toString());
            reservation.setSeat(Integer.valueOf(seat.getText()));
            System.out.println(reservation);


            try {
                if(reservationService.seatOcupated(reservation.getMovieName(),reservation.getDate(),reservation.getSeat(),reservation.getDay())){

                    message.setText("Seat Unavailable");

                }else{
                    reservationService.addReservationToDatabase(reservation);
                    message.setText("Registration done");
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }else{
            System.out.println(message.getText());
            message.setText("Must complete all fields");
        }

    }
}