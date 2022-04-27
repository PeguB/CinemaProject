package org.fis.cinemaregistrationapplication.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.Main;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.User;
import org.fis.cinemaregistrationapplication.services.SceneSwitcher;
import org.fis.cinemaregistrationapplication.services.UserLoginService;

import java.io.IOException;

public class HelloController {
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
        role.setValue("Client");
    }

    @FXML
    protected void onRegisterClick() {
        User U = new User(usernameField.getText(), passwordField.getText(), role.getValue().toString());
        try {
            if(passwordField.getText() == "" || usernameField.getText() == "")
                registrationMessage.setText("Credentials can not be empty!");
            else {
                registrationMessage.setText("Account created succesfully, try to login now : )");
                UserLoginService.addUserToDatabase(U);
                usernameField.setText("");
                passwordField.setText("");
                role.setValue(null);
            }
        }catch (UsernameAlreadyExistsException e){
            registrationMessage.setText("Username " + U.getUsername() + " already exists!");
        }catch (Exception e){
            registrationMessage.setText(e.toString());
        }
    }

    @FXML
    protected void onLoginClick() throws IOException {
        if(UserLoginService.checkCredIfExists(usernameField.getText(), passwordField.getText(), role.getValue().toString())){

            UserLoginService.setLoggedIn(usernameField.getText(), role.getValue().toString());
            if(role.getValue().toString().equals("Admin")){
                SceneSwitcher.switchScene("homePageManager.fxml");
            }else{
                SceneSwitcher.switchScene("homePageClient.fxml");
            }

        }else{
            registrationMessage.setText("Your credentials are invalid");
        }
    }
}