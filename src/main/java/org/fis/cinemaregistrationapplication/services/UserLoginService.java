package org.fis.cinemaregistrationapplication.services;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.User;
import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import javafx.fxml.FXML;


public class UserLoginService {

    public static void addUserToDatabase(User u) throws UsernameAlreadyExistsException{
        try {
            String query = "INSERT INTO users(Username, Role, Password) VALUES('" + u.getUsername() + "', '" + u.getRole() + "', '" + u.getPassword() + "')";
            DBConnection.doStatement(query);
        }catch (UsernameAlreadyExistsException e){
            throw new UsernameAlreadyExistsException("");
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
