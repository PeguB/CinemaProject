package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.fis.cinemaregistrationapplication.models.Reservation;
import org.fis.cinemaregistrationapplication.services.*;

import java.sql.SQLException;
import java.util.List;

public class EditReservationController {

    MoviesService moviesService = new MoviesService();
    ReservationService reservationService = new ReservationService();

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox id;

    @FXML
    private ComboBox seat;

    @FXML
    private ComboBox day;

    @FXML
    private ComboBox hour;

    @FXML
    private ComboBox movieName;

    @FXML
    private Label message;

    @FXML
    public void goHome(){
        SceneSwitcher.switchScene("HomePageManager.fxml");
    }

    @FXML
    public void initialize(){
        List<String> listReservationComfirmed = reservationService.getReservationsIdWhitStatusComfirmed();
        if(listReservationComfirmed.isEmpty()){
            message.setText("No reservations to check");
        }else{
            id.getItems().addAll(reservationService.getReservationsIdWhitStatusComfirmed());
            String predefineMovieNameValue = moviesService.getMoviesName().stream().iterator().next();
            movieName.setValue(predefineMovieNameValue);
            hour.setValue(moviesService.getStartDateByMovieName(movieName.getValue().toString()).get(0));
            hour.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
            day.getItems().addAll(DaysGenerator.getDays());
            seat.getItems().addAll(SeatGenerator.getList());
        }


    }

    @FXML
    public void setHoursBaseByMovieName() {
        if (movieName.getValue().toString() != null) {
            hour.getItems().clear();
            hour.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));
        }
        movieName.setOnAction(event -> {
            hour.getItems().clear();
            hour.getItems().addAll(moviesService.getStartDateByMovieName(movieName.getValue().toString()));

        });
    }

    @FXML
    public void onSaveButton() {
        if (hour.getValue().toString() != null && movieName.getValue().toString() != null && !seat.getValue().equals(null)
                && CheckNumber.isNumeric(seat.getValue().toString()) && Integer.parseInt(seat.getValue().toString()) < 51 && Integer.parseInt(seat.getValue().toString()) > 0) {
            try {
                if (reservationService.availableSeatByNumberOfSeats(movieName.getValue().toString(), hour.getValue().toString(), day.getValue().toString()))
                {
                    Reservation reservation = new Reservation();

                    reservation.setDate(hour.getValue().toString());
                    reservation.setMovieName(movieName.getValue().toString());
                    reservation.setDay(day.getValue().toString());
                    reservation.setSeat(Integer.valueOf(seat.getValue().toString()));



                    try {
                        if (reservationService.seatOcupated(reservation.getMovieName(), reservation.getDate(), reservation.getSeat(), reservation.getDay())) {

                            message.setText("Seat Unavailable");

                        } else {
                            Integer Id = Integer.parseInt(id.getValue().toString());
                            reservationService.updateReservation(Id,reservation.getDate(),reservation.getMovieName(),reservation.getDay(),reservation.getSeat());
                            message.setText("Update done");
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                    }catch (RuntimeException E){
                        message.setText("Empty field");
                    }

                } else {
                    System.out.println(message.getText());
                    message.setText("The room is full try another date or movie");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                id.getItems().clear();
                id.getItems().addAll(reservationService.getReservationsIdWhitStatusComfirmed());
            }

        }else {
            System.out.println(message.getText());
            message.setText("Invalid fields");
        }

    }
}
