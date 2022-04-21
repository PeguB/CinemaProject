package org.fis.cinemaregistrationapplication;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DBConnection.CreateConnection();
        launch();
        DBConnection.closeConnection();
    }
}