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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserLoginService {

    private static User LoggedIn = new User();

    public static void addUserToDatabase(User u) throws UsernameAlreadyExistsException{
        try {
            String query = "INSERT INTO users(Username, Role, Password) VALUES(?, ?, ?)";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, u.getUsername());
            statement.setString(3, encodePassword(u.getUsername(), u.getPassword()));
            statement.setString(2, u.getRole());
            statement.executeUpdate();
        }catch (java.sql.SQLIntegrityConstraintViolationException e){
            throw new UsernameAlreadyExistsException("");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean checkCredIfExists(String username, String pass, String role){
        boolean value = true;
        try{
            String query = "SELECT * FROM users WHERE Username='" + username + "';";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                if(!result.getString(3).equals(encodePassword(username, pass)))
                    value = false;
                if(!result.getString(2).equals(role))
                    value = false;
            } else{
                value = false;
            }
        }catch(Exception e){
            System.out.println(e);
        }finally {
            return value;
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("'", ""); //to be able to save in JSON format
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

    public static String usernameLoggedIn(){
        return LoggedIn.getUsername();
    }

    public static String roleLoggedIn(){
        return LoggedIn.getRole();
    }

    public static void setLoggedIn(String username, String role){
        LoggedIn.setUsername(username);
        LoggedIn.setRole(role);
    }

    public List<String> getUsersFromDatabase(){
        List<String> list = new ArrayList<>();
        try{
            String query  = "SELECT * FROM users WHERE ROLE ='Client';";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list.add(result.getString("username"));
            }
        }catch(Exception e){
            System.out.println(e);
        }


        return list;
    }
}
