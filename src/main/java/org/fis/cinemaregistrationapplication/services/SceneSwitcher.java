package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.Main;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(String fxml){
        try{
            Main m = new Main();
            m.switchScene(fxml);
        }catch (IOException e){}
    }
}
