package services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;
import org.fis.cinemaregistrationapplication.models.User;
import org.fis.cinemaregistrationapplication.services.UserLoginService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserLoginServiceTests {

    UserLoginService userLoginService = new UserLoginService();
    @BeforeAll
    public static void initialize(){
        DBConnection.CreateConnection();
    }


    @AfterAll
    public static void close(){
        DBConnection.closeConnection();
    }

    public void deleteUser(String username){
        String query = "DELETE FROM users WHERE Username = ?";
        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setString(1, username);
            int result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findUser(String username){

        String query = "SELECT * FROM users where Username = '"+ username+ "'";
        User user = new User();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
               user.setUsername(result.getString("Username"));
               user.setRole(result.getString("Role"));
               user.setPassword(result.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Test
    public void addUserToDatabaseTest() throws UsernameAlreadyExistsException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("Client");

        deleteUser(user.getUsername());
        UserLoginService.addUserToDatabase(user);
        Assertions.assertEquals(user.getUsername(),findUser(user.getUsername()).getUsername());
        deleteUser(user.getUsername());


    }

    @Test
    public void checkCredIfExistsTest() throws UsernameAlreadyExistsException {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("Client");

        deleteUser(user.getUsername());
        UserLoginService.addUserToDatabase(user);
        Boolean exists = UserLoginService.checkCredIfExists(user.getUsername(),user.getPassword(),user.getRole());
        Assertions.assertTrue(exists);
        deleteUser(user.getUsername());
        Boolean existsBeforeDelete = UserLoginService.checkCredIfExists(user.getUsername(),user.getPassword(),user.getRole());
        Assertions.assertFalse(existsBeforeDelete);
    }

    @Test
    public void getUsersFromDatabaseTest() throws UsernameAlreadyExistsException {
       List<String> users = userLoginService.getUsersFromDatabase();

        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("Client");

        deleteUser(user.getUsername());
        UserLoginService.addUserToDatabase(user);

        List<String> newUsers = userLoginService.getUsersFromDatabase();
        Assertions.assertEquals(users.size() + 1,newUsers.size());
        Assertions.assertTrue(user.equals(user));
        deleteUser(user.getUsername());
    }
}
