package org.fis.cinemaregistrationapplication;

import javafx.scene.Parent;
import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginscreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }

    public static void main(String[] args) {
        DBConnection.CreateConnection();
        launch();
        DBConnection.closeConnection();
    }

    public void switchScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(pane);
    }
}