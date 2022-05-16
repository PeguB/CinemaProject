package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

public class MovieInformationController {

    @FXML
    private Text movieName;

    @FXML
    private Text movieDuration;

    @FXML
    private Text movieHour;

    @FXML
    private ImageView movieImage;

    @FXML
    public void initialize(){
        movieName.setText(MoviesService.ActualMovie.getName());
        movieDuration.setText(MoviesService.ActualMovie.getDuration_min().toString());
        movieHour.setText(MoviesService.ActualMovie.getStart_date());
        initImg();
    }

    @FXML
    public void initImg() {
        try {
            String imageSource = MoviesService.getMovieImage(MoviesService.ActualMovie.getName());
            Image image = new Image(imageSource);
            movieImage.setImage(image);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void onTakeMe(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    public void onGoBack(){
        SceneSwitcher.switchScene("homePageClient.fxml");
    }



}
