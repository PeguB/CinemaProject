package services;

import org.fis.cinemaregistrationapplication.dbconnection.DBConnection;
import org.fis.cinemaregistrationapplication.models.Movie;
import org.fis.cinemaregistrationapplication.services.MoviesService;
import org.fis.cinemaregistrationapplication.services.ReservationService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;

public class MovieServiceTests {


    private MoviesService moviesService = new MoviesService();



    @BeforeAll
    public static void initialize(){
        DBConnection.CreateConnection();
    }

    @AfterAll
    public static void close(){
        DBConnection.closeConnection();
    }

    @Test
    public void getMovieByNameTest() throws SQLException {
        Movie movie = new Movie("Batman",3,"10:00");

        Movie reality = MoviesService.getMovieByName("Batman");
        Assertions.assertEquals(movie,reality);
    }

    @Test
    public void getStartDateByMovieNameTest(){
        List<String> hours = new ArrayList<>();
        hours.add("10:00");
        hours.add("11:00");
        Assertions.assertEquals(hours,moviesService.getStartDateByMovieName("Batman"));
    }

    @Test
    public void getMoviesName(){
        Set<String> names = new HashSet<>();
        names.add("Batman");
        Assertions.assertEquals(names,moviesService.getMoviesName());
    }

    @Test
    public void  getAllMoviesTest() throws SQLException {
        ResultSet resultSetInitial = MoviesService.getAllMovies();
        assert true;
    }

}
