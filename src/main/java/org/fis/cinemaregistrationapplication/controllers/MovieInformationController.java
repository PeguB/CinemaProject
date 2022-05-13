package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class MovieInformationController {

    @FXML
    private Text movieName;

    @FXML
    private Text movieDuration;

    @FXML
    private Text movieHour;

    @FXML
    private Text movieSeats;

    @FXML
    private Image movieImage;

    @FXML
    public void initImg() {
        String imageSource = ;
        Image image = new Image(imageSource);
        pkmnImg.setImage(image);

    }

}
