package org.fis.cinemaregistrationapplication.dbconnection;

import org.fis.cinemaregistrationapplication.exceptions.UsernameAlreadyExistsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    public static Connection connection;

    public static void CreateConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/accounts", "root", "parola"
            );

        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void doStatement(String stat) throws Exception{
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(stat);
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            throw new UsernameAlreadyExistsException("");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection returnConnection(){
        return connection;
    }
}