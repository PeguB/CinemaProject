package org.fis.cinemaregistrationapplication.services;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.User;
import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import javafx.fxml.FXML;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class UserLoginService {

    public static void addUserToDatabase(User u) throws UsernameAlreadyExistsException{
        try {
            String query = "INSERT INTO users(Username, Role, Password) VALUES('" + u.getUsername() + "', '" + u.getRole() + "', '" + encodePassword(u.getUsername(), u.getPassword()) + "')";
            DBConnection.doStatement(query);
        }catch (UsernameAlreadyExistsException e){
            throw new UsernameAlreadyExistsException("");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
