package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.*;

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
    private Text AvailableCheck;

    @FXML
    public void initialize(){

        ArrayList<Integer> Seats = new ArrayList<Integer>();
        for(int i = 1; i < 49; i++)
            Seats.add(i);

        seat.getItems().addAll(Seats);
        seat.setValue("1");
        day.getItems().addAll(DaysGenerator.getDays());
        day.setValue("Monday");

        Set<String> Movies = moviesService.getMoviesName();
        if(!Movies.isEmpty()){
            movie.getItems().addAll(Movies);
            movie.setValue(Movies.iterator().next());
            hour.getItems().addAll(moviesService.getStartDateByMovieName(movie.getValue().toString()));
            hour.setValue(moviesService.getStartDateByMovieName(movie.getValue().toString()).iterator().next());
        }

    }

    @FXML
    protected void onCheckAction(){
        try {
            if(reservationService.seatOcupated(movie.getValue().toString(), hour.getValue().toString(), (Integer) seat.getValue(), day.getValue().toString())) {
                AvailableCheck.setFill(Color.RED);
                AvailableCheck.setText("Seat Unavailable");
            } else{
                AvailableCheck.setFill(Color.GREEN);
                AvailableCheck.setText("Seat Available");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    protected void onBookAction(){
        Reservation Rez = new Reservation();
        Rez.setSeat((Integer) seat.getValue());
        Rez.setDay(day.getValue().toString());
        Rez.setDate(hour.getValue().toString());
        Rez.setUsername_user(SingletonUsername.getUSSERNAME());
        Rez.setMovieName(movie.getValue().toString());
        Rez.setConfirmed("Unknown");

        try {
            if (!reservationService.seatOcupated(movie.getValue().toString(), hour.getValue().toString(), (Integer) seat.getValue(), day.getValue().toString())){
                reservationService.addReservationToDatabase(Rez);
                AvailableCheck.setFill(Color.ORANGE);
                AvailableCheck.setText("Seat booked succesfully");
            } else{
                AvailableCheck.setFill(Color.ORANGE);
                AvailableCheck.setText("Please Check your seat availabilit again!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    protected void onHomeAction(){
        SceneSwitcher.switchScene("homePageClient.fxml");
    }

    @FXML
    protected void onAddAction(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    protected void onLogOutAction(){
        SceneSwitcher.switchScene("loginscreen.fxml");
    }

    @FXML
    protected void clickOnInfo(){
        SceneSwitcher.switchScene("ClientReservationInfo.fxml");
    }
}
