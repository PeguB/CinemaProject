package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;

import java.sql.ResultSet;

public class ClientReservationInfoController {

    @FXML
    protected void clickOnAdd(){
        SceneSwitcher.switchScene("ClientAddReservation.fxml");
    }

    @FXML
    protected void clickOnInfo(){
        SceneSwitcher.switchScene("ClientReservationInfo.fxml");
    }

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void initialize() {

        try{
            ResultSet rooms = RoomService.getRooms();
            int row = 1;
            while(rooms.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("oneRoom.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                Room room = new Room();
                room.setType(rooms.getString(1));
                room.setNrPers(rooms.getInt(2));
                room.setPrice(rooms.getInt(3));
                room.setImg(rooms.getString(5));
                OneRoomController oneRoomController = fxmlLoader.getController();
                oneRoomController.setData(room);
                grid.add(anchorPane, 0, row++); //(child,column,row)

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        }catch (SQLException | IOException e) {
            System.out.println(e);
        }

    }
}
