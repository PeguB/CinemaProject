package org.fis.cinemaregistrationapplication.services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;

import java.sql.*;
import java.util.*;

public class MoviesService {

    public static String ActualMovieName;

    public Set<String> getMoviesName(){

            String query = "SELECT * FROM movie";
            Set<String> setMovies = new HashSet<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                 setMovies.add(result.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setMovies;
    }
    public List<String> getStartDateByMovieName(String MovieName){
        String query = "SELECT * FROM movie";
        HashMap<String,List<String>> dictionaryHourMovies = new HashMap<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                String start_date = result.getString("start_date");
                String movie = result.getString("name");
                if(dictionaryHourMovies.containsKey(movie)){

                    dictionaryHourMovies.get(movie).add(start_date);
                }else{
                    ArrayList<String> list = new ArrayList<>();
                    list.add(start_date);
                    dictionaryHourMovies.put(movie,list);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dictionaryHourMovies.get(MovieName);
    }

    public static ResultSet getAllMovies() throws SQLException{
        String query = "SELECT * FROM movie";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        ResultSet movies = statement.executeQuery();

        return movies;
    }

    public static ResultSet getMovieImage(String MovieName) throws SQLException{
        String query = "SELECT * FROM moviephoto WHERE name = ?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
        statement.setString(1, MovieName);

        ResultSet movie = statement.executeQuery();
        return movie;
    }

    public static void setActualName(String name){
        ActualMovieName = name;
    }
}
